package org.example.split;

import org.example.model.User;

public class PercentSplit implements Split {
    private final User user;
    private final double percentage;
    private double amount;

    public PercentSplit(User user, double percentage) {
        this.user = user;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
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
