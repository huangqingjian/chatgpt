package com.chatgpt.exception;

/**
 * 自定义异常
 */
public class SecurityException extends RuntimeException{
    public SecurityException() {
        super();
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
