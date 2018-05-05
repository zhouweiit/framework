package org.zhouwei.framework.task;

/**
 * 任务的统一Exception
 *
 * @author zhouwei
 */
public class TaskException extends Exception{
	
	private static final long serialVersionUID = 5403249031559539202L;
	
	public TaskException(String message) {
        super(message);
    }
	
	public TaskException(String message,Exception e) {
        super(message,e);
    }
	
}
