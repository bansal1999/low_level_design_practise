package org.example.Model;

import java.util.Map;

public class Warehouse {
    private Inventory inventory;
    private Address address;

    public Warehouse(Inventory inventory, Address address) {
        this.inventory = inventory;
        this.address = address;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Address getAddress() {
        return address;
    }

    public void removeItemFromInventory(Map<Integer, Integer> productCategoryAndCountMap) {
    }

    public void addItemToInventory(Map<Integer, Integer> productCategoryAndCountMap) {
    }


}
