package org.zhouwei.framework.task;

import java.util.Date;

import lombok.Data;

/**
 * 任务的统一配置类
 *
 * @author zhouwei
 */
@Data
public class TaskConfig {
	
	/**
	 * 表达式调度
	 */
	public static final int CRON_TYPE = 1;
	
	/**
	 * 周期调度
	 */
	public static final int PERIOD_TYPE = 2;
	
	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 节点ID
	 */
	private Integer nodeId;
	
	/**
	 * 任务名称
	 */
	private String taskName;
	
	/**
	 * 任务类名称
	 */
	private String className;
	
	/**
	 * 调度计划
	 */
	private String cron;
	
	/**
	 * 调度类型
	 */
	private Integer cronType;
	
	/**
	 * 开始时间
	 */
	private Date beginDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 任务配置
	 */
	private Property property;

}
