package com.techu.apitechu.utils;

import org.springframework.http.HttpStatus;

public enum PurchaseEnum {
    // TODO: incorporate error codes

    PURCHASE_NOT_CREATED ("Couldn´t create purchase", HttpStatus.INTERNAL_SERVER_ERROR);
    private final String message;
    private final HttpStatus statusCode;
    PurchaseEnum(String message, HttpStatus statusCode) {
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
