package com.techu.apitechu.models;

public class GeneralModel {
    private String errorMessage;

    public GeneralModel() {
    }

    public GeneralModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
