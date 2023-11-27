package com.techu.apitechu.error;

public class ProductException extends Exception {
    public ProductException(String pointOfOrigin, String message) {
        super(String.format("Product Error originated in %s with message: %s", pointOfOrigin, message));
    }
}
