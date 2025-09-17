package org.example.Model;

import org.example.Enums.OrderStatus;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int nextOrderId = 1;

    private int orderId;
    private User user;
    private Address deliveryAddress;
    private Map<Integer, Integer> productCategoryAndCountMap;
    private Warehouse warehouse;
    private Invoice invoice;
    private Payment payment;
    private OrderStatus orderStatus;

    public Order(User user, Warehouse warehouse) {
        this.user = user;
        this.warehouse = warehouse;
        this.orderId = nextOrderId++;
        this.deliveryAddress = user.getAddress();
        this.productCategoryAndCountMap = new HashMap<>(user.getUserCart().getProductCategoryIdVsCountMap());
        this.invoice = new Invoice();
        this.orderStatus = OrderStatus.PENDING;
        user.addOrderIds(orderId);
    }

    public void checkout() {
        if (orderStatus == OrderStatus.PENDING) {
            try {
                warehouse.removeItemFromInventory(productCategoryAndCountMap);
                generateOrderInvoice();
                orderStatus = OrderStatus.PROCESSING;
            } catch (Exception e) {
                orderStatus = OrderStatus.FAILED;
            }
        }
    }

    public void generateOrderInvoice(){
        invoice.generateInvoice(this);
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Map<Integer, Integer> getProductCategoryAndCountMap() {
        return productCategoryAndCountMap;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
