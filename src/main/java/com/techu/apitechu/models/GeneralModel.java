package com.techu.apitechu.models;

public class GeneralModel {

    // TODO: add HttpMethod here so I can return messages and statuses instead of throwing errors everywhere
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
