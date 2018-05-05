package org.zhouwei.framework.service;

/**
 * Service层的统一抽象接口
 *
 * @author zhouwei
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = -5459563333058712266L;

	public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
