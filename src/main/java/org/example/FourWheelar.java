package org.example;

import org.example.enums.VehicleType;

public class FourWheelar extends Vehicle {
    public FourWheelar(String licencePlate) {
        super(licencePlate, VehicleType.FOUR_WHEELER);
    }
}
