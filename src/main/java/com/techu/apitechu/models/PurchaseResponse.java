package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

public class PurchaseResponse {
    private String errorMessage;
    private HttpStatus httpStatus;
    private PurchaseModel payload;

    public PurchaseResponse() {
    }

    public PurchaseResponse(String errorMessage, HttpStatus httpStatus, PurchaseModel payload) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.payload = payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public PurchaseModel getPayload() {
        return payload;
    }

    public void setPayload(PurchaseModel payload) {
        this.payload = payload;
    }
}
