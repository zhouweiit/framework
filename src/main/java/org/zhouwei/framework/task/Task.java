package org.zhouwei.framework.task;

import org.zhouwei.framework.dao.DaoException;

/**
 * 所有计划任务都需要实现的接口
 * 
 * @author zhouwei
 *
 */
public interface Task {
	
	/**
	 * 任务入口方法
	 * @param property
	 * @return
	 */
	public void execute(Property property) throws DaoException;

}
