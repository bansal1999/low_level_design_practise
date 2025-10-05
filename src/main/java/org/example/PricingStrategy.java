package org.example;

import org.example.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class PricingStrategy {
    private Map<VehicleType, Double> hourlyRates;

    public PricingStrategy() {
        hourlyRates = new HashMap<>();
        hourlyRates.put(VehicleType.FOUR_WHEELER, 20.0);
        hourlyRates.put(VehicleType.TWO_WHEELER, 10.0);
        hourlyRates.put(VehicleType.TRUCK, 50.0);
    }

    public double calculatePrice(VehicleType type, long hours) {
        double rate = hourlyRates.getOrDefault(type, (double) 0);
        return rate * Math.max(1, hours);
    }

    public void updateRate(VehicleType type, double rate) {
        hourlyRates.put(type, rate);
    }
}
