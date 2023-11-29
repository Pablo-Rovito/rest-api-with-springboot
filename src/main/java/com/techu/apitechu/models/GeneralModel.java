package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

public class GeneralModel {

    // TODO: add HttpMethod here so I can return messages and statuses instead of throwing errors everywhere
    private String errorMessage;
    private HttpStatus httpStatus;

    public GeneralModel() {
    }

    public GeneralModel(String errorMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
    public HttpStatus getHttpStatus() { return httpStatus; }
}
