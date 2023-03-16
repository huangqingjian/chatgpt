package com.chatgpt.exception;

/**
 * 自定义可用异常
 */
public class AvailableException extends RuntimeException{
    private Integer code;

    public AvailableException() {
        super();
    }

    public AvailableException(String message) {
        super(message);
    }

    public AvailableException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AvailableException(Throwable cause) {
        super(cause);
    }

    public AvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public AvailableException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
