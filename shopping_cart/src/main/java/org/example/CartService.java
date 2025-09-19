package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CartService {
    private final Map<String, Cart> userCarts;
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.userCarts = new ConcurrentHashMap<>();
        this.productService = productService;
    }

    public Cart getOrCreateCart(String userId) {
        return userCarts.computeIfAbsent(userId, id -> new Cart("CART_" + id, id));
    }

    public boolean addToCart(String userId, String productId, int quantity) {
        Product product = productService.getProduct(productId);
        if (product == null || !product.isAvailable(quantity)) {
            return false;
        }
        Cart cart = getOrCreateCart(userId);
        cart.addItem(product, quantity);
        return true;
    }

    public boolean updateCartItem(String userid, String productId, int quantity) {
        Cart cart = userCarts.get(userid);
        if (cart != null) {
            if (quantity > 0) {
                Product product = productService.getProduct(productId);
                if (product != null && product.isAvailable(quantity)) {
                    cart.updateItem(productId, quantity);
                    return true;
                }
            } else {
                cart.removeItem(productId);
                return true;
            }

        }
        return false;
    }


    public void removeFromCart(String userId, String productId) {
        Cart cart = userCarts.get(userId);
        if (cart != null) {
            cart.removeItem(productId);
        }
    }

    public Cart getCart(String userId) {
        return userCarts.get(userId);
    }

    public void clearCart(String userId) {
        Cart cart = userCarts.get(userId);
        if (cart != null) {
            cart.clearItems();
        }
    }

}
