package com.brucecompiler.exception;

public class DeletionNotAllowedException extends BaseException{
    public DeletionNotAllowedException(Integer code, String message) {
        super(code, message);
    }
}
