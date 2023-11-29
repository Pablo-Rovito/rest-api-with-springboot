package com.techu.apitechu.models;

import java.util.Map;

public class PurchaseModel extends GeneralModel {
    private static int instances;
    private String id;
    private String userId;
    private Float amount;
    private Map purchaseItems;

    public PurchaseModel() {
        this.id = String.format("purchase-%s", instances);
        instances++;
    }

    public PurchaseModel(String errorMessage) {
        super(errorMessage);
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Map getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(Map purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
