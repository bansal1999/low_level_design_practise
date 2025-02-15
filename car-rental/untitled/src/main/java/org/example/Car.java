package org.example;

public class Car {
    private final String make;
    private final String model;
    private final int year;
    private final String LicensePlate;
    private final double rentalPricePerDay;
    private boolean available;


    public Car(String model, String make, int year, String licensePlate, double rentalPricePerDay) {
        this.model = model;
        this.make = make;
        this.year = year;
        LicensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.available = true;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
