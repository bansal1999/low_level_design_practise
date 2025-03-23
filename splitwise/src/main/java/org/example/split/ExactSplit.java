package org.example.split;

import org.example.model.User;

public class ExactSplit implements Split {
    private User user;
    private double amount;

    public ExactSplit(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
