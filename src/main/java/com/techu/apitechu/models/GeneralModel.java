package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

public class GeneralModel {

    // TODO: turn this into a general service response, so abstract validations work better and can be iterated.
    private String errorMessage;
    private HttpStatus httpStatus;

    public GeneralModel() {
    }

    public GeneralModel(String errorMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
