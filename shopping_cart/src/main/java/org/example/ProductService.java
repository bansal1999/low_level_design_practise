package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductService {

    private final Map<String, Product> products;

    public ProductService() {
        this.products = new ConcurrentHashMap<>();
        initialiseProducts();
    }

    private void initialiseProducts() {
        products.put("001", new Product("001", "Laptop", "Gameing Laptop", 999.99, "Electrinis", 10));
        products.put("002", new Product("002", "Mouse", "Wireless Mouse", 29.99, "Electronics", 12));
        products.put("003", new Product("003", "Book", "Java Code", 79.99, "Books", 20));
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public synchronized void updateStock(String productId, int quantityUsed) {
        Product product = products.get(productId);
        if (product != null) {
            int newStock = product.getStockQuantity() - quantityUsed;
            product.setStockQuantity(Math.max(0, newStock));
        }
    }

    public boolean isProductAvailable(String productId, int quantity) {
        Product product = products.get(productId);
        return product != null && product.isAvailable(quantity);
    }


}
