package com.brucecompiler.exception;

public class BaseException extends RuntimeException{
    private final Integer code;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
