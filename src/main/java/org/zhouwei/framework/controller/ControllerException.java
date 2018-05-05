package org.zhouwei.framework.controller;

/**
 *
 * Controller的通用Exception接口
 *
 * @author zhouwei
 *
 */
public class ControllerException extends Exception{

	private static final long serialVersionUID = -1429963268401111966L;

	public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    protected ControllerException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
