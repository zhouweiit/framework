package org.zhouwei.framework.task;

/**
 * 任务加载完成之后，回调类的接口
 *
 * @author zhouwei
 */
public interface TaskCallBack {
	
	public void call() throws TaskException;

}
