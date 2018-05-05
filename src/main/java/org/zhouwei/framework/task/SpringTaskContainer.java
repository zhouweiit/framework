package org.zhouwei.framework.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 依赖于spring schedule的轻量级任务容器
 * 
 * @author zhouwei
 *
 */
@Slf4j
public class SpringTaskContainer implements SchedulingConfigurer, ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 任务句柄,关联任务ID
	 */
	private Map<Long, ScheduledFuture<?>> taskHolderMap = new HashMap<Long, ScheduledFuture<?>>();

	/**
	 * 任务配置句柄,关联任务ID
	 */
	private Map<Long, TaskConfig> taskConfigMap = new HashMap<Long, TaskConfig>();

	/**
	 * spring容器
	 */
	private ApplicationContext context;

	/**
	 * 注入给spring的调度线程执行容器
	 */
	private ScheduledThreadPoolExecutor executor;

	/**
	 * spring任务调度器
	 */
	private ConcurrentTaskScheduler scheduler;
	
	/**
	 * 需要注册的
	 */
	private List<TaskCallBack> calls = Lists.newArrayList();

	/**
	 * 线程池的大小
	 */
	private int poolSize = 1;

	public SpringTaskContainer() {
	}

	public SpringTaskContainer(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * 初始化一些任务来处理
	 */
	private void init() {
		try {
			//注入清理无效任务的清理作业
			scheduler.scheduleAtFixedRate(new TaskWrapper(new TaskCheck(), null), 60000l);
			for (TaskCallBack call : calls){
				call.call();
			}
		} catch (Exception e) {
			log.error("SpringTaskContainer init fail",e);
		}
	}
	
	/**
	 * 注册需要回调的一次性事情
	 */
	public void registerCallBack(TaskCallBack call){
		if (call != null){
			calls.add(call);
		}
	}
	
	/**
	 * 批量加载任务
	 * 
	 * @param list
	 * @author ZHOUWEI
	 */
	public void schedule(List<TaskConfig> list) {
		for (TaskConfig taskConfig : list) {
			this.schedule(taskConfig);
		}
	}

	/**
	 * 加载任务
	 *
	 * @param taskConfig
	 * @author ZHOUWEI
	 */
	public boolean schedule(TaskConfig taskConfig) {
		try {
			if (isScheduled(taskConfig)) {//作业已经加载，先卸载，确保配置完成加载
				this.unSchedule(taskConfig);
			}

			Class<?> classTask = Class.forName(taskConfig.getClassName());
			Class<?>[] interfaces = classTask.getInterfaces();
			boolean taskType = false;
			for (Class<?> i : interfaces) {
				if (Task.class.getName().equals(i.getName())) {
					taskType = true;
					break;
				}
			}
			
			if (!taskType) {//作业没有实现Task接口
				throw new TaskException(taskConfig.getClassName() + " is not a task");
			}
			
			try {
				context.getBean(classTask);
			} catch (Exception e){//作业没有注入到spring容器
				throw new TaskException(taskConfig.getClassName() + " not inject to spring container or more than ont instance",e);
			}
			
			Task task = (Task) context.getBean(classTask);
			TaskWrapper wrapper = new TaskWrapper(task, taskConfig);
			ScheduledFuture<?> future = null;
			if (taskConfig.getCronType() == TaskConfig.CRON_TYPE) {
				future = scheduler.schedule(wrapper, new CronTrigger(taskConfig.getCron()));
			} else {
				//固定频率
				//future = scheduler.scheduleAtFixedRate(wrapper, Integer.valueOf(taskConfig.getCron()) * 1000);
				//等待上一次结束然后顺延
				future = scheduler.scheduleWithFixedDelay(wrapper, Integer.valueOf(taskConfig.getCron()) * 1000);
			}
			this.addMap(taskConfig.getId(), future, taskConfig);
			log.info("SpringTaskContainer schedule sucess,taskConfig:{}", taskConfig);
			return true;
		} catch (Exception e) {
			log.error("SpringTaskContainer schedule faile,taskConfig:{}", taskConfig, e);
			return false;
		}
	}

	/**
	 * 批量卸载任务
	 * 
	 * @param list
	 * @author zhouwei
	 */
	public void unSchedule(List<TaskConfig> list) {
		for (TaskConfig taskConfig : list) {
			this.unSchedule(taskConfig);
		}
	}

	/**
	 * 卸载任务
	 * 
	 * @param taskConfig
	 * @author zhouwei
	 */
	public boolean unSchedule(TaskConfig taskConfig) {
		try {
			if (isScheduled(taskConfig)) {
				ScheduledFuture<?> futuer = this.taskHolderMap.get(taskConfig.getId());
				futuer.cancel(false);
				this.removeMap(taskConfig.getId());
				log.info("SpringTaskContainer unschedule taskconfig:{}", taskConfig);
			}
			return true;
		} catch (Exception e) {
			log.error("SpringTaskContainer unSchedule faile,taskConfig:{}", taskConfig, e);
			return false;
		}
	}

	/**
	 * 清空所有任务信息
	 * 
	 * @author zhouwei
	 */
	public void unScheduleAll() {
		for (Long id : taskConfigMap.keySet()) {
			unSchedule(taskConfigMap.get(id));
		}
	}

	/**
	 * 任务是否在调度
	 * 
	 * @param taskConfig
	 * 
	 * @author zhouwei
	 */
	public boolean isScheduled(TaskConfig taskConfig) {
		if (!this.taskHolderMap.containsKey(taskConfig.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * 添加map信息
	 * 
	 * @param id
	 * @param schedule
	 * @param config
	 * 
	 * @author zhouwei
	 */
	private void addMap(Long id, ScheduledFuture<?> schedule, TaskConfig config) {
		taskHolderMap.put(id, schedule);
		taskConfigMap.put(id, config);
	}

	/**
	 * 移除map信息
	 * 
	 * @param id
	 * 
	 * @author zhouwei
	 */
	private void removeMap(Long id) {
		taskHolderMap.remove(id);
		taskConfigMap.remove(id);
	}

	/**
	 * 任务检查 1.清理过期的任务 2.清理无效的任务句柄
	 * 
	 * @author zhouwei
	 */
	class TaskCheck implements Task {

		public void execute(final Property property) {
			this.removeExpireTask();
			this.removeAbnormalTask();
		}

		/**
		 * 清除超时的任务
		 * 
		 * @author zhouwei
		 */
		private void removeExpireTask() {
			for (Long id : taskHolderMap.keySet()) {
				TaskConfig taskConfig = taskConfigMap.get(id);
				if (taskConfig.getEndDate() != null && taskConfig.getEndDate().getTime() < System.currentTimeMillis()) {
					ScheduledFuture<?> scheduledFuture = taskHolderMap.get(id);
					scheduledFuture.cancel(false);
					SpringTaskContainer.this.removeMap(id);
					log.info("removeExpireTask:{}", taskConfig);
				}
			}
		}

		/**
		 * 清除异常的任务
		 * 
		 * @author zhouwei
		 */
		private void removeAbnormalTask() {
			/**
			 * 清除取消异常的任务
			 * 
			 * @author zhouwei
			 */
			for (Long id : taskHolderMap.keySet()) {
				ScheduledFuture<?> scheduledFuture = taskHolderMap.get(id);
				if (scheduledFuture.isCancelled()) {
					TaskConfig taskConfig = taskConfigMap.get(id);
					SpringTaskContainer.this.removeMap(id);
					log.info("removeAbnormalTask:{}", taskConfig);
				}
			}
		}
	}

	/**
	 * 注册spring容器初始化完成事件，获取spring容器上下文
	 * 
	 * @author zhouwei
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		context = event.getApplicationContext();
	}

	/**
	 * 注入schedule
	 * 
	 * @author zhouwei
	 */
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		this.executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(this.poolSize);
		this.scheduler = new ConcurrentTaskScheduler(executor);
		taskRegistrar.setScheduler(scheduler);
		this.init();
	}
}