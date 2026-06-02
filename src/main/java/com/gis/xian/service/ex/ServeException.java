package com.gis.xian.service.ex;

/**
 * @author zzw
 * @description: 服务异常
 * @date 2026/5/25 下午5:41
 */
public class ServeException extends ServiceException {
    public ServeException() {
    }

    public ServeException(String message) {
        super(message);
    }

    public ServeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServeException(Throwable cause) {
        super(cause);
    }

    public ServeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
