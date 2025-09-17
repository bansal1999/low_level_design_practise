package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ProductCategory> productCategoryList;

    public Inventory(List<ProductCategory> productCategoryList) {
        this.productCategoryList = new ArrayList<>();
    }

    public void addCategory(int categoryId, String name, int price) {
        ProductCategory category = new ProductCategory(categoryId, name, price);
        productCategoryList.add(category);
    }

    public void addProduct(Product product, int productCategoryId) {
        ProductCategory category = getProductCategoryId(productCategoryId);
        if (category != null) {
            category.addProduct(product);
        } else {
            throw new IllegalArgumentException("Product category not found");
        }
    }

    public void removeProduct(Product product, int productCategoryId) {
        ProductCategory category = getProductCategoryId(productCategoryId);
        if (category != null) {
            category.removeProduct(product);
        } else {
            throw new IllegalArgumentException("Product category not found");
        }
    }

    private ProductCategory getProductCategoryId(int categoryId) {
        for (ProductCategory category : productCategoryList) {
            if (category.getProductCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }
}
