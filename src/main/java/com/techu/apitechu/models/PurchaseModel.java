package com.techu.apitechu.models;

import com.techu.apitechu.utils.PurchaseStatuses;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class PurchaseModel extends GeneralModel {
    private static final int DAYS_PAYMENT_DUE = 15;
    private static int instances;
    private String id;
    private String userId;
    private Float amount;
    private Map purchaseItems;
    // TODO: Dates can be put into an object
    private Date purchaseDate;
    private Date lastPurchaseDate;
    private Date paymentDate;
    private ArrayList<PaymentModel> payments;
    private Enum<PurchaseStatuses> status;
    private String errorMessage;

    public PurchaseModel(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage, httpStatus);
        this.id = String.format("purchase-%s", instances);
        instances++;
    }

    public PurchaseModel() {
        this.id = String.format("purchase-%s", instances);
        instances++;
    }

    public PurchaseModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public PurchaseModel(String userId, Float amount, Map purchaseItems, Date purchaseDate, Date paymentDate, Date lastPurchaseDate, ArrayList<PaymentModel> payments, Enum<PurchaseStatuses> status) {
        this.id = String.format("purchase-%s", instances);
        this.userId = userId;
        this.amount = amount;
        this.purchaseItems = purchaseItems;
        this.purchaseDate = purchaseDate;
        this.paymentDate = paymentDate;
        this.lastPurchaseDate = lastPurchaseDate;
        this.payments = payments;
        this.status = status;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setPurchaseItems(Map purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public ArrayList<PaymentModel> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<PaymentModel> payments) {
        this.payments = payments;
    }

    public Enum<PurchaseStatuses> getStatus() {
        return status;
    }

    public void setStatus(Enum<PurchaseStatuses> status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public int getDaysPaymentDue() {
        return DAYS_PAYMENT_DUE;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
