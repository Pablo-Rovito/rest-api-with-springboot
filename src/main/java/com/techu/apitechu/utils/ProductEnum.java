package com.techu.apitechu.utils;

import org.springframework.http.HttpStatus;

public enum ProductEnum {
    // TODO: incorporate error codes
    PRODUCT_NOT_DELETED ("Couldn´t delete product", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_FOUND ("Product not found!", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_IN_LIST ("A product with that ID already exists!", HttpStatus.METHOD_NOT_ALLOWED),
    DELETE_NUCLEAR_PRODUCT ("Cannot delete a nuclear product!", HttpStatus.METHOD_NOT_ALLOWED),
    DELETE_PRODUCT_PRICE_TOO_HIGH ("Product´s price is too high to delete it!", HttpStatus.METHOD_NOT_ALLOWED),

    PRODUCT_NOT_CREATED ("Couldn´t create product!", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_UPDATED ("Couldn´t update product!", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus statusCode;
    ProductEnum(String message, HttpStatus statusCode) {
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
