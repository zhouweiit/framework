package org.zhouwei.framework.dao;

/**
 * 统一的Dao层的Exception
 *
 * @author zhouwei
 */
public class DaoException extends Exception{

	private static final long serialVersionUID = -5459563333058712266L;

	public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    protected DaoException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
