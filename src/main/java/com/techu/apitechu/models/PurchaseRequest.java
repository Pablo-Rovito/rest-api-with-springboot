package com.techu.apitechu.models;

import java.util.HashMap;

public class PurchaseRequest {
    private String userId;
    private HashMap<String, Float> purchaseItems;

    public String getUserId() {
        return userId;
    }

    public HashMap<String, Float> getPurchaseItems() {
        return purchaseItems;
    }
}
