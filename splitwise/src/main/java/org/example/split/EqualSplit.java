package org.example.split;

import org.example.model.User;

public class EqualSplit implements Split {
    private final User user;
    private double amount;

    public EqualSplit(User user) {
        this.user = user;
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
