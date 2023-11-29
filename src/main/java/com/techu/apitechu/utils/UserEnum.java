package com.techu.apitechu.utils;

import com.techu.apitechu.validators.UserValidations;
import org.springframework.http.HttpStatus;

public enum UserEnum {
    // TODO: incorporate error codes

    USER_ALREADY_IN_LIST ("A user with that ID already exists!", HttpStatus.METHOD_NOT_ALLOWED),
    USER_NOT_IN_LIST ("There isn´t a user with that ID!", HttpStatus.NOT_FOUND),
    USER_NOT_CREATED ("Couldn´t create user!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_DELETED ("Couldn´t delete user!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_UPDATED ("Couldn´t update user!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_TOO_OLD (String.format("Can´t delete users of over %S!", UserValidations.MAX_AGE), HttpStatus.METHOD_NOT_ALLOWED);


    private final String message;
    private final HttpStatus statusCode;
    UserEnum(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
