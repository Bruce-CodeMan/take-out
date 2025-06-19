package com.brucecompiler.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final Integer code;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
