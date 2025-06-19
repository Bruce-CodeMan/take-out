package com.brucecompiler.constant;

/**
 * Unified Status Codes
 * Defines standard response status codes for API responses
 */
public class StatusCodeConstant {

    // Success status code
    public static final Integer SUCCESS = 1;

    // General error status code
    public static final Integer FAILURE = 0;

    // HTTP standard status code
    public static final Integer UNAUTHORIZED = 401;     // User is not authenticated
    public static final Integer FORBIDDEN = 403;        // User lacks necessary permissions
    public static final Integer NOT_FOUND = 404;        // Requested resource not found
    public static final Integer PARAM_ERROR = 400;      // Invalid request parameters
    public static final Integer SYSTEM_ERROR = 500;     // Internal server error

    // Business-related status code
    public static final Integer LOGIN_ERROR = 1000;     // Username or password incorrect
    public static final Integer ACCOUNT_LOCKED = 1001;  // User account is locked
    public static final Integer USER_EXIST = 1002;      // User is existed
}
