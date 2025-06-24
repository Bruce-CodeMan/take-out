package com.brucecompiler.exception;

public class LoginFailedException extends BaseException{

    public LoginFailedException(Integer code, String message) {
        super(code, message);
    }
}
