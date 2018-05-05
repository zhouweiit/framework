package org.zhouwei.framework.task;

import lombok.extern.slf4j.Slf4j;

/**
 * 任务包装类
 * 
 * @author zhouwei
 */
@Slf4j
public class TaskWrapper implements Runnable{
	
	/**
	 * 任务实例,spring容器里面的单例
	 */
	private Task task;
	
	/**
	 * 任务配置信息
	 */
	private TaskConfig taskConfig;
	
	/**
	 * 任务类名称
	 */
	private String className;
	
	/**
	 * 任务配置信息
	 */
	private Property property;
	
	public TaskWrapper(Task task,TaskConfig config){
		this.task = task;
		this.taskConfig = config;
		this.init();
	}
	
	public void init(){
		if (null != taskConfig){
			property = taskConfig.getProperty();
		}
		className = task.getClass().getName();
	}
	
	/**
	 * 任务启动入口
	 */
	public void run() {
		//任务未到开始时间，直接退出
		if (taskConfig != null && taskConfig.getBeginDate() != null && taskConfig.getBeginDate().getTime() > System.currentTimeMillis()){
			log.info("taskclass:{},Not to start ! time:{},taskConfig:{}",className,taskConfig.getBeginDate(),taskConfig);
			return;
		}
		if (taskConfig != null && taskConfig.getEndDate() != null && taskConfig.getEndDate().getTime() < System.currentTimeMillis()){
			log.info("taskclass:{},has end ! time:{},taskConfig:{}",className,taskConfig.getBeginDate(),taskConfig);
			return;
		}
		long start = System.currentTimeMillis();
		log.info("taskclass:{},taskconfig:{}",className,taskConfig);
		try {
			if (property != null){
				task.execute(property.clone());
			} else {
				task.execute(new Property("{}","{}"));
			}
		} catch (Throwable e) {
			log.error("taskclass:{}",className,e);
		}
		long end = System.currentTimeMillis();
		log.info("taskclass:{},elapse:{}",className,end - start);
	}

}