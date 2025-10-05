package org.example;

import org.example.enums.VehicleType;

public class Truck extends  Vehicle{
    public Truck(String licencePlate) {
        super(licencePlate, VehicleType.TRUCK);
    }
}
