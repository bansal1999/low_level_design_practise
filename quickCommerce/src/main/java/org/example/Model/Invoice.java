package org.example.Model;

import java.util.Map;

public class Invoice {
    private int totalPrice;
    private int totalTax;
    private int finalPrice;

    public void generateInvoice(Order order) {
        Map<Integer, Integer> items = order.getProductCategoryAndCountMap();
        Warehouse warehouse = order.getWarehouse();
        Inventory inventory = warehouse.getInventory();
        totalPrice = 0;



        totalTax = (int) (totalPrice * 0.18);
        finalPrice = totalPrice + totalTax;

    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTotalTax() {
        return totalTax;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
}
