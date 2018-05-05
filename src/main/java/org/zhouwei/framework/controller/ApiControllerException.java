package org.zhouwei.framework.controller;

/**
 *
 * ApiController的Exception接口
 *
 * @author zhouwei
 *
 */
public class ApiControllerException extends Exception{
	
	private static final long serialVersionUID = -5853629243995193348L;

	public ApiControllerException() {
        super();
    }

    public ApiControllerException(String message) {
        super(message);
    }

    public ApiControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiControllerException(Throwable cause) {
        super(cause);
    }

    protected ApiControllerException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
