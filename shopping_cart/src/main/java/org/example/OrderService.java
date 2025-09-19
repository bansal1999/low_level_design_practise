package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OrderService {
    private final Map<String, Order> orders;
    private final Map<String, List<String>> userOrders;
    private final ProductService productService;
    private final CartService cartService;
    private int orderIdCounter = 1;

    public OrderService(ProductService productService, CartService cartService) {
        this.orders = new ConcurrentHashMap<>();
        this.userOrders = new ConcurrentHashMap<>();
        this.productService = productService;
        this.cartService = cartService;
    }

    public synchronized Order createOrder(String userId, String shippingAddress) {
        Cart cart = cartService.getOrCreateCart(userId);
        if (cart == null || cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        //Verify item availability
        for (CartItem item : cart.getItems()) {
            if (!productService.isProductAvailable(item.getProductId(), item.getQuantity())) {
                throw new RuntimeException("Product " + item.getProductName() + " is not available");
            }
        }

        //Create order items
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> new OrderItem(cartItem.getProductId(), cartItem.getProductName(),
                        cartItem.getUnitPrice(), cartItem.getQuantity()))
                .toList();

        String orderId = "ORD" + String.format("%04d", orderIdCounter++);
        Order order = new Order(orderId, userId, orderItems, cart.getTotalAmount(), shippingAddress);

        //update Inventory
        for(CartItem item: cart.getItems()){
            productService.updateStock(item.getProductId(), item.getQuantity());
        }

        //Store order
        orders.put(orderId, order);
        userOrders.computeIfAbsent(userId, k -> new ArrayList<>()).add(orderId);

        //clear cart
        cartService.clearCart(userId);

        return order;
    }

    public Order getOrder(String orderId){
        return orders.get(orderId);
    }

    public void updateOrderStatus(String orderId, OrderStatus status){
        Order order = orders.get(orderId);
        if(order != null){

        }
    }


}
