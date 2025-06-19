package com.brucecompiler.exception;

public class LoginErrorException extends BaseException{

    public LoginErrorException(Integer code, String message) {
        super(code, message);
    }
}
