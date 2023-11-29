package com.techu.apitechu.models;

import java.util.Map;

public class PurchaseRequest {
    private String userId;
    private Map purchaseItems;

    public String getUserId() {
        return userId;
    }

    public Map getPurchaseItems() {
        return purchaseItems;
    }
}
