package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class PurchaseModel extends GeneralModel {
    private static int instances;
    private String id;
    private String userId;
    private Float amount;
    private Map purchaseItems;

    public PurchaseModel(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage, httpStatus);
    }

    public PurchaseModel(String userId, Float amount, Map purchaseItems) {
        this.id = String.format("purchase-%s", instances);
        this.userId = userId;
        this.amount = amount;
        this.purchaseItems = purchaseItems;
        instances++;
    }

    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public Float getAmount() {
        return amount;
    }
    public Map getPurchaseItems() {
        return purchaseItems;
    }
}
