package org.example.Controller;

import org.example.Model.Warehouse;
import org.example.WarehouseSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class WarehouseController {
    private List<Warehouse> warehouseList;
    private WarehouseSelectionStrategy warehouseSelectionStrategy;

    public WarehouseController(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy) {
        this.warehouseList = warehouseList != null ? warehouseList : new ArrayList<>();
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouseList.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouseList.remove(warehouse);
    }

    public Warehouse selectWarehouse(WarehouseSelectionStrategy warehouseSelectionStrategy) {
        return warehouseSelectionStrategy.selectWarehouse(warehouseList);
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }
}
