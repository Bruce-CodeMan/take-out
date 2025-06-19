package com.brucecompiler.result;

import java.io.Serializable;

import com.brucecompiler.constant.StatusCodeConstant;
import lombok.Data;

/**
 * Generic result wrapper for API Response
 * Provides a standard format for all API returns
 *
 * @param <T> Type of data contained in the result
 *
 * @author Bruce Compiler
 */
@Data
public class Result<T> implements Serializable {

    // Status code indicating the result of the operation
    private Integer code;

    // Describe message about the result
    private String message;

    // The actual data returned by the API
    private T data;

    /**
     * Creates a success result containing the specified data
     *
     * @return A {@link Result} object with success status code
     * @param <T> Type of data which would be contained in the result
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = StatusCodeConstant.SUCCESS;
        return result;
    }

    /**
     * Creates a success result containing the specified data
     *
     * @param data Type of data which contains in the result
     * @return The data to be returned
     * @param <T> A {@link Result} object with success status code and the provided data
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = StatusCodeConstant.SUCCESS;
        result.data = data;
        return result;
    }

    /**
     * Creates an error result with the specified error code and message
     *
     * @param code The error code representing the type of error
     * @param message The error message describing the problem
     * @return A {@link Result} object with the specified error code and message
     * @param <T> Type of data which would be contained in the result
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }
}
