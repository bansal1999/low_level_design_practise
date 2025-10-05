package org.example;

import org.example.enums.ParkingSpotType;
import org.example.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpotList;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpotList = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotList.add(parkingSpot);
    }

    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType) {
        ParkingSpotType requiredSpotType = getRequiredSpotType(vehicleType);
        for (ParkingSpot spot : parkingSpotList) {
            if (spot.isAvailable() && spot.getParkingSpotType() == requiredSpotType) {
                return spot;
            }
        }
        return null;
    }

    private ParkingSpotType getRequiredSpotType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case TWO_WHEELER -> ParkingSpotType.TWO_WHEELER;
            case FOUR_WHEELER -> ParkingSpotType.COMPACT;
            case TRUCK -> ParkingSpotType.LARGE;
            default -> null;
        };
    }

    public int getAvailableSpot(ParkingSpotType type) {
        int count = 0;
        for (ParkingSpot spot : parkingSpotList) {
            if (spot.isAvailable() && spot.getParkingSpotType() == type) {
                count++;
            }
        }
        return count;
    }


}
