package com.brucecompiler.exception;

import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brucecompiler.result.Result;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global exception handler for centralizing API error response
 * Automatically intercepts exceptions thrown from controllers
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles custom application exception
     *
     * @param exception The custom exception thrown by the application
     * @return A Result object containing the error code and message
     */
    @ExceptionHandler
    public Result<Object> exceptionHandler(BaseException exception) {
        return Result.error(exception.getCode(), exception.getMessage());
    }

    /**
     * Handles SQL integrity constraint violations
     *
     * @param exception The SQLIntegrityConstraintViolationException thrown by the database
     * @return A Result object containing the error code and message
     */
    @ExceptionHandler
    public Result<Object> doSQLException(SQLIntegrityConstraintViolationException exception) {
        log.error("SQL Exception: {}", exception.getMessage());
        String message = exception.getMessage();
        if(message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String msg = split[2];
            msg = msg.replace("'", "");
            return Result.error(StatusCodeConstant.FAILURE, msg + " " + MessageConstant.USER_EXISTS);
        }
        return Result.error(StatusCodeConstant.FAILURE, MessageConstant.UNKNOWN_ERROR);
    }
}
