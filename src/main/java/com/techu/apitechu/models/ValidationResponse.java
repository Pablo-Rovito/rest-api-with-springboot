package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    private boolean success;
    private List<String> messages;
    private HttpStatus httpStatus;
    private PurchaseModel payload;

    public ValidationResponse() {
        this.success = true;
        this.httpStatus = HttpStatus.OK;
        this.payload = new PurchaseModel();
    }

    public ValidationResponse(boolean success, List<String> messages, HttpStatus httpStatus, PurchaseModel payload) {
        this.success = success;
        this.messages = messages;
        this.httpStatus = httpStatus;
        this.payload = payload;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        if(this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }
}
