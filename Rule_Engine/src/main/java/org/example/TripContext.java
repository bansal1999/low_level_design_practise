package org.example;

import java.util.List;

public class TripContext {
    private String tripId;
    private List<Expense> expenses;
    private double totalAmount;

    public TripContext(String tripId, List<Expense> expenses) {
        this.tripId = tripId;
        this.expenses = expenses;
        this.totalAmount = expenses.stream().mapToDouble(Expense::getAmountUsd).sum();
    }

    public String getTripId() {
        return tripId;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
