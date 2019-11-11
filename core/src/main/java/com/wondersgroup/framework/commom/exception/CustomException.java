package com.wondersgroup.framework.commom.exception;

/**
 * @author chenlin
 * @create 2019-06-28 16:22
 * @description: 自定义异常类，项目中均抛出此异常
 * @version：1.0
 **/
public class CustomException extends RuntimeException {


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
