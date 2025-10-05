package org.example;

import org.example.enums.VehicleType;

public class TwoWheelar extends Vehicle {
    public TwoWheelar(String licencePlate) {
        super(licencePlate, VehicleType.TWO_WHEELER);
    }
}
