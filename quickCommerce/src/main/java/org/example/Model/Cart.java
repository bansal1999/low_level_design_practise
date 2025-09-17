package org.example.Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> productCategoryIdVsCountMap;

    public Cart() {
        this.productCategoryIdVsCountMap = new HashMap<>();
    }

    public void addItemToCart(int productCategoryId, int count) {
        productCategoryIdVsCountMap.put(productCategoryId,
                productCategoryIdVsCountMap.getOrDefault(productCategoryId, 0) + count);
    }

    public void removeItemFromCart(int productCategoryId, int count) {
        if (productCategoryIdVsCountMap.containsKey(productCategoryId)) {
            int currentCount = productCategoryIdVsCountMap.get(productCategoryId);
            if (currentCount <= count) {
                productCategoryIdVsCountMap.remove(productCategoryId);
            } else {
                productCategoryIdVsCountMap.put(productCategoryId, count - currentCount);
            }
        } else {
            throw new IllegalArgumentException("Product category not found in the cart");
        }
    }

    public void clearCart() {
        productCategoryIdVsCountMap.clear();
    }

    public Map<Integer, Integer> getProductCategoryIdVsCountMap() {
        return new HashMap<>(productCategoryIdVsCountMap);
    }
}
