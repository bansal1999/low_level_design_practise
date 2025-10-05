package org.example;

import org.example.enums.ParkingSpotStatus;
import org.example.enums.ParkingSpotType;

public class ParkingSpot {
    private String spotId;
    private ParkingSpotType parkingSpotType;
    private Vehicle vehicle;
    private ParkingSpotStatus parkingSpotStatus;

    public ParkingSpot(String spotId, ParkingSpotType parkingSpotType) {
        this.spotId = spotId;
        this.parkingSpotType = parkingSpotType;
        this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
    }

    public boolean isAvailable(){
        return parkingSpotStatus == ParkingSpotStatus.AVAILABLE;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.parkingSpotStatus = ParkingSpotStatus.OCCUPIED;
    }

    public void removeVehicle(){
        this.vehicle = null;
        this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
