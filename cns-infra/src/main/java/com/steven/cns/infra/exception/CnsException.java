package com.steven.cns.infra.exception;

/**
 * @author steven.cao
 */
public class CnsException extends RuntimeException {

    public CnsException() {
    }

    public CnsException(String message) {
        super(message);
    }

    public CnsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CnsException(Throwable cause) {
        super(cause);
    }

    public CnsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
