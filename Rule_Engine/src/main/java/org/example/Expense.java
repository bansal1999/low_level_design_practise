package org.example;

public class Expense {
    private String expenseId;
    private String tripId;
    private double amountUsd;
    private String expenseType;
    private String vendorName;

    public Expense(String expenseId, String tripId, double amountUsd, String expenseType, String vendorName) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.amountUsd = amountUsd;
        this.expenseType = expenseType;
        this.vendorName = vendorName;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getTripId() {
        return tripId;
    }

    public double getAmountUsd() {
        return amountUsd;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public String getVendorName() {
        return vendorName;
    }
}
