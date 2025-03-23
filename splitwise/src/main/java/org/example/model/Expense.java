package org.example.model;

import org.example.enums.Splittype;
import org.example.split.Split;

import java.util.Date;
import java.util.List;

public class Expense {
    private String id;
    private double amount;
    private String description;
    private  User paidBy;
    private List<Split> splits;
    private Splittype splittype;
    private Date createdAt;

    public Expense(String id, double amount, String description, User paidBy, List<Split> splits, Splittype splittype, Date createdAt) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.paidBy = paidBy;
        this.splits = splits;
        this.splittype = splittype;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public Splittype getSplittype() {
        return splittype;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
