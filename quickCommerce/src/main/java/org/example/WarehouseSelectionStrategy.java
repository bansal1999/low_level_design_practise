package org.example;

import org.example.Model.Warehouse;

import java.util.List;

public interface WarehouseSelectionStrategy {
    public Warehouse selectWarehouse(List<Warehouse> warehouseList);
}
