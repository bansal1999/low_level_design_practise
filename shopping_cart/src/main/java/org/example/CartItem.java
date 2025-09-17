package org.example;

public class CartItem {
    private String productId;
    private String productName;
    private double unitPrice;
    private int quantity;

    public CartItem(String productId, String productName, double unitPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("CartItem{productId='%s', name='%s', quantity=%d, total=%.2f}",
                productId, productName, quantity, getTotalPrice());
    }

}
