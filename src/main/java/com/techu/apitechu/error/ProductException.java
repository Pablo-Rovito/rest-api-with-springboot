package com.techu.apitechu.error;

import org.springframework.http.HttpStatus;

public class ProductException extends Exception {

    private final HttpStatus statusCode;
    public ProductException(String pointOfOrigin, String message, HttpStatus statusCode) {
        super(String.format("ProductException originated in %s with message: %s", pointOfOrigin, message));
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
