package com.techu.apitechu.utils;

import org.springframework.http.HttpStatus;

public enum PurchaseEnum {
    // TODO: incorporate error codes

    PURCHASE_NOT_CREATED ("Couldn´t create purchase", HttpStatus.INTERNAL_SERVER_ERROR),
    PURCHASE_NOT_FOUND ("Couldn´t find purchase", HttpStatus.NOT_FOUND),
    PAYMENT_TOO_BIG ("The payment is too big! amount due: ", HttpStatus.METHOD_NOT_ALLOWED),
    PAYMENT_TOO_LATE ("The payment is too late! the purchase has been cancelled", HttpStatus.METHOD_NOT_ALLOWED),
    USER_NOT_REGISTERED ("Can´t buy without registered user", HttpStatus.METHOD_NOT_ALLOWED),
    PURCHASE_ALREADY_REGISTERED ("A purchase with that Id has already been registered", HttpStatus.METHOD_NOT_ALLOWED),
    ILLEGAL_PRODUCTS ("At least one product is not present in internal Product list.", HttpStatus.METHOD_NOT_ALLOWED);
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
