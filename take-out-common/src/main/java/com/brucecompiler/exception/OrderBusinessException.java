package com.brucecompiler.exception;

public class OrderBusinessException extends BaseException{

    public OrderBusinessException(Integer code, String message) {
        super(code, message);
    }
}
