package org.example.split;

import org.example.model.User;

public interface Split {
    User getUser();

    double getAmount();

    void setAmount(double amount);
}
