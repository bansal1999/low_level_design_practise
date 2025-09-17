package org.example;

import org.example.Model.Address;
import org.example.Model.Warehouse;

import java.util.List;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {
    private Address userAddress;

    public NearestWarehouseSelectionStrategy(Address userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
        if (warehouseList != null && !warehouseList.isEmpty()) {
            return warehouseList.get(0);
        }
        return null;
    }
}
