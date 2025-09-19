package org.example;

import java.util.List;

public class ShoppingCartSystem {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public ShoppingCartSystem() {
        this.productService = new ProductService();
        this.userService = new UserService();
        this.cartService = new CartService(productService);
        this.orderService = new OrderService(productService, cartService);
    }

    // User operations
    public User registerUser(String username, String email, String password, String address) {
        return userService.registerUser(username, email, password, address);
    }

//    public User authenticateUser(String username, String password) {
//        return userService.authenticateUser(username, password);
//    }

    // Product operations
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
//
//    public List<Product> getProductsByCategory(String category) {
//        return productService.getProductsByCategory(category);
//    }

    public Product getProduct(String productId) {
        return productService.getProduct(productId);
    }

    // Cart operations
    public void addToCart(String userId, String productId, int quantity) {
        cartService.addToCart(userId, productId, quantity);
    }

    public boolean updateCartItem(String userId, String productId, int quantity) {
        return cartService.updateCartItem(userId, productId, quantity);
    }

    public void removeFromCart(String userId, String productId) {
        cartService.removeFromCart(userId, productId);
    }

    public Cart getCart(String userId) {
        return cartService.getCart(userId);
    }

    // Order operations
    public Order checkout(String userId, String shippingAddress) {
        return orderService.createOrder(userId, shippingAddress);
    }

//    public List<Order> getUserOrders(String userId) {
//        return orderService.getUserOrders(userId);
//    }

    public Order getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");

        ShoppingCartSystem system = new ShoppingCartSystem();

        // Register user
        User user = system.registerUser("john_doe", "john@email.com", "password123", "123 ShoppingCartSystem St");
        System.out.println("User registered: " + user);

        // Browse products
        System.out.println("\nAvailable Products:");
        system.getAllProducts().forEach(System.out::println);

        // Add items to cart
        system.addToCart(user.getUserId(), "P001", 1);
        system.addToCart(user.getUserId(), "P002", 2);

        // View cart
        Cart cart = system.getCart(user.getUserId());
        System.out.println("\nCart Contents:");
        //cart.getItems().forEach(System.out::println);
        System.out.println("Total: $" + cart.getTotalAmount());

        // Checkout
        Order order = system.checkout(user.getUserId(), user.getAddress());
        System.out.println("\nOrder Created: " + order.getOrderId());
        System.out.println("Order Total: $" + order.getTotalAmount());
    }
}