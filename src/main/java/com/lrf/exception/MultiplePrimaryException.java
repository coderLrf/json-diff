package com.lrf.exception;

/**
 * 多个主键异常
 *
 * @author lrf
 */
public class MultiplePrimaryException extends RuntimeException {

    public MultiplePrimaryException() {
        super();
    }
    
    public MultiplePrimaryException(String msg) { super(msg); }
    
    public MultiplePrimaryException(String msg, Throwable cause) { super(msg, cause); }

}
