package org.zhouwei.framework.api;

/**
 *
 * 调用第三方API产生的通过exception
 *
 * @author zhouwei
 *
 */
public class ApiException extends Exception{

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    protected ApiException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
