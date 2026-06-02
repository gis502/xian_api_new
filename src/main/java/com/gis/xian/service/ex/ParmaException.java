package com.gis.xian.service.ex;

/**
 * @author zzw
 * @description: 参数异常处理
 * @date 2026/5/25 下午5:36
 */
public class ParmaException extends ServiceException{
    public ParmaException() {
    }

    public ParmaException(String message) {
        super(message);
    }

    public ParmaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParmaException(Throwable cause) {
        super(cause);
    }

    public ParmaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
