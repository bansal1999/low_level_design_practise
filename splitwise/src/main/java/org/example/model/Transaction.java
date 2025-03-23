package org.example.model;

import java.util.Date;

public class Transaction {
    private String id;
    private User from;
    private User to;
    private double amount;
    private Date createdAt;

    public Transaction(String id, User from, User to, double amount, Date createdAt) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        return "Transaction{" +
                "id='" + id + '\'' +
                ", from=" + from.getName() +
                ", to=" + to.getName() +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
