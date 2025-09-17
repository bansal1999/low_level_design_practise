package org.example;

public class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;


    public Product(String productId, String name, String description, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, stock=%d}",
                productId, name, price, stockQuantity);
    }

    public boolean isAvailable(int quantity){
        return stockQuantity >= quantity;
    }





}
