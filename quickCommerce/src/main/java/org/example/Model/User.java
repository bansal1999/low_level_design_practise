package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String userName;
    private Address address;
    private Cart userCart;
    private List<Integer> orderIds;

    public User(int userId, String userName, Address address) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.userCart = new Cart();
        this.orderIds = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Address getAddress() {
        return address;
    }

    public Cart getUserCart() {
        return userCart;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void addOrderIds(int orderId) {
        orderIds.add(orderId);
    }
}
