package com.brucecompiler.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brucecompiler.result.Result;

/**
 * Global exception handler for centralizing API error response
 * Automatically intercepts exceptions thrown from controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<Object> exceptionHandler(BaseException exception) {
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
