package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private int productCategoryId;
    private String productCategoryName;
    private List<Product> products;
    private double price;
    private int stock;

    public ProductCategory(int productCategoryId, String productCategoryName, double price) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.price = price;
        this.stock = 0;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        stock++;
    }

    public void removeProduct(Product product) {
        products.remove(product);
        stock--;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
