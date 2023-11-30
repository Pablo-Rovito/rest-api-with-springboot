package com.techu.apitechu.models;

import java.util.Date;
import java.util.HashMap;

public class PaymentModel {
    private static int instances;
    private final String id;
    private String userId;
    private String purchaseId;
    private HashMap<String, Float> purchaseItems;
    private Date paymentDate;
    private Float amount;

    public PaymentModel() {
        this.id = "payment-" + instances;
        instances++;
    }

    public PaymentModel(Date paymentDate, Float amount) {
        this.id = "payment-" + instances;
        this.paymentDate = paymentDate;
        this.amount = amount;
        instances++;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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

    public HashMap<String, Float> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(HashMap<String, Float> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}
