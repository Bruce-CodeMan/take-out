package com.brucecompiler.exception;

public class AccountLockedException extends BaseException{

    public AccountLockedException(Integer code, String message) {
        super(code, message);
    }
}
