package com.techu.apitechu.models;

import java.util.Map;

public class PurchaseModel extends GeneralModel {
    private String id;
    private String userId;
    private Float amount;
    private Map purchaseItems;

    public PurchaseModel() {
    }

    public PurchaseModel(String errorMessage) {
        super(errorMessage);
    }

    public PurchaseModel(String errorMessage, String id, String userId, Float amount, Map purchaseItems) {
        super(errorMessage);
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.purchaseItems = purchaseItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
