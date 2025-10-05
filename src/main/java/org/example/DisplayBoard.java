package org.example;

import org.example.enums.ParkingSpotType;

public class DisplayBoard {
    private int floorNumber;

    public DisplayBoard(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void displayAvailability(ParkingFloor floor) {
        System.out.println("==== Floor " + floorNumber + "====");
        System.out.println("Two Wheeler Spots: " +
                floor.getAvailableSpot(ParkingSpotType.TWO_WHEELER));
        System.out.println("Compact Spots: " +
                floor.getAvailableSpot(ParkingSpotType.COMPACT));
        System.out.println("Large Spots: " +
                floor.getAvailableSpot(ParkingSpotType.LARGE));
        System.out.println("====================");
    }
}
