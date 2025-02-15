package org.example;

//Product Manager to manage catalog of products

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class ProductManager {
    private final Map<String, Product> productCatalog;

    public ProductManager() {
        this.productCatalog = new HashMap<>();
    }

    //add a new product to the catalog
    public void addProduct(String productId, String productName, int initialQuantity) {
        if (productCatalog.containsKey(productId)) {
            throw new IllegalArgumentException("Product ID already exists: " + productId);
        }
        Product product = new Product(productId, productName, initialQuantity);
        productCatalog.put(productId, product);
    }

    //Get the product by id
    public Product getProduct(String productId) {
        return productCatalog.get(productId);
    }

    //get all the products
    public Map<String, Product> getAllProducts() {
        return new HashMap<>(productCatalog);
    }
}


//Product class for each item in the inventory
class Product {
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    private final String productId;
    private final String productName;
    private int totalQuantity;
    private int reserveQuantity;

    public Product(String productId, String productName, int totalQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.reserveQuantity = 0;
    }

    //checks if enough quantity is available
    public boolean isAvailableQuantity(int requestedQuantity) {
        return (totalQuantity - reserveQuantity) >= requestedQuantity;
    }

    //reserve the quantity of the order
    public void reserveQuantity(int quantity) {
        if (!isAvailableQuantity(quantity)) {
            throw new IllegalStateException("Quantity is not available" + productId);
        }
        this.reserveQuantity += quantity;
    }

    //confirm order
    public void confirmOrder(int quantity) {
        this.reserveQuantity -= quantity;
        this.totalQuantity -= quantity;
    }

    public int getAvailableQuantity() {
        return totalQuantity - reserveQuantity;
    }

}

class OrderManager {
    private final Map<String, Order> orderRegister;
    private final ProductManager productManager;

    public OrderManager(ProductManager productManager) {
        this.orderRegister = new HashMap<>();
        this.productManager = productManager;
    }

    public Order createOrder(Map<String, Integer> orderItems, String orderId) {
        Map<Product, Integer> validatedItems = new HashMap<>();
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            Product product = productManager.getProduct(entry.getKey());

            if (!product.isAvailableQuantity(entry.getValue())) {
                throw new IllegalStateException("Not enough stock for product: " + entry.getKey());
            }

            validatedItems.put(product, entry.getValue());
        }
        Order order = new Order(validatedItems, orderId);
        order.blockInventory();
        orderRegister.put(orderId, order);
        return order;
    }

    public void confirmOrder(String orderId) {
        Order order = orderRegister.get(orderId);
        if (order != null) {
            throw new NoSuchElementException("order not found");
        }
        order.confirmOrder();
    }


}

class Order {
    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    private final String orderId;
    private final LocalDateTime createdAt;
    private final Map<Product, Integer> orderItems;
    private OrderStatus status;

    private enum OrderStatus {
        CONFIRMED,
        CANCELLED,
        CREATED,
        INVENTORY_BLOCKED,
        PAYMENT_PENDING
    }

    public Order(Map<Product, Integer> orderItems, String orderId) {
        this.orderItems = new HashMap<>(orderItems);
        this.orderId = orderId;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public void blockInventory() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot block inventory for an order that is not in created state");
        }

        //reserve quantity for each product
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            entry.getKey().reserveQuantity(entry.getValue());
        }
        this.status = OrderStatus.INVENTORY_BLOCKED;

    }

    public void confirmOrder() {
        if (status != OrderStatus.INVENTORY_BLOCKED) {
            throw new IllegalStateException("Cannot confirm order for an order that is not in inventory blocked state");
        }

        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            entry.getKey().confirmOrder(entry.getValue());
        }

        this.status = OrderStatus.CONFIRMED;
    }
}

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        ProductManager productManager = new ProductManager();
        productManager.addProduct("1", "Laptop", 10);
        productManager.addProduct("2", "Mobile", 20);

        OrderManager orderManager = new OrderManager(productManager);

        Map<String, Integer> orderItems = new HashMap<>();
        orderItems.put("1", 2);
        orderItems.put("2", 3);



        try {
            Product laptop = productManager.getProduct("1");
            Product mobile = productManager.getProduct("2");
            System.out.println("lAPTOP inventory: " + laptop.getAvailableQuantity());
            System.out.println("Mobile inventory: " + mobile.getAvailableQuantity());

            //create and confirm order
            String orderId = orderManager.createOrder(orderItems, "ORDER_001").getOrderId();

            //check inventory after blocking
            System.out.println("Laptop after blocking " + productManager.getProduct("1").getAvailableQuantity());
            System.out.println("phone after blocking " + productManager.getProduct("2").getAvailableQuantity());

            orderManager.confirmOrder(orderId);

            //final inventory check
            System.out.println("Laptop after order " + productManager.getProduct("1").getAvailableQuantity());
            System.out.println("phone after order " + productManager.getProduct("2").getAvailableQuantity());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}