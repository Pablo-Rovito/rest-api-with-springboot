package com.techu.apitechu.utils;

public enum PurchaseStatuses {
    COMPLETED ("Completed"),
    OVERDUE ("Overdue"),
    PENDING ("Pending");

    private final String state;

    PurchaseStatuses(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
