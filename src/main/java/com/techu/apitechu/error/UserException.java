package com.techu.apitechu.error;

import org.springframework.http.HttpStatus;

public class UserException extends Exception {
    private final HttpStatus statusCode;
    public UserException(String pointOfOrigin, String message, HttpStatus statusCode) {
        super(String.format("UserException originated in %s with message: %s", pointOfOrigin, message));
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
