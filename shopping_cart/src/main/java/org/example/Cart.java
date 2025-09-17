package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private String userId;
    private String cartId;
    private Map<String, CartItem> items;

    public Cart(String userId, String cartId) {
        this.userId = userId;
        this.cartId = cartId;
        this.items = new ConcurrentHashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quqntiy");
        }

        String productId = product.getProductId();
        CartItem existingItem = items.get(productId);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(productId, product.getName(), product.getPrice(), quantity);
            items.put(productId, newItem);
        }
    }

    public void updateItem(String productId, int quantity) {
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }

        CartItem item = items.get(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public void removeItem(String productId) {

        items.remove(productId);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearItems() {
        items.clear();
    }

    public double getTotalAmount() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public int getTotalItem() {
        return items.values().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

}
