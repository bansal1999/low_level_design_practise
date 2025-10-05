package org.example;

import org.example.enums.ParkingSpotType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //initialise parking lot
        ParkingLot parkingLot = ParkingLot.getInstance();

        //create floors and add spots
        for (int i = 1; i <= 2; i++) {
            ParkingFloor floor = new ParkingFloor(i);

            //add spots for each floor
            for (int j = 1; j <= 10; j++) {
                floor.addParkingSpot(new ParkingSpot("F" + i + "TW" + j, ParkingSpotType.TWO_WHEELER));
            }

            for (int j = 1; j <= 20; j++) {
                floor.addParkingSpot(new ParkingSpot("F" + i + "-C-" + j, ParkingSpotType.COMPACT));
            }

            for (int j = 1; j <= 5; j++) {
                floor.addParkingSpot(new ParkingSpot("F" + i + "-L-" + j, ParkingSpotType.LARGE));
            }

            parkingLot.addFloor(floor);
        }

        // Display initial availability
        System.out.println("\nInitial Parking Availability:");
        parkingLot.displayAllFloors();

        // Park vehicles
        Vehicle bike = new TwoWheelar("MH12AB1234");
        Vehicle car = new FourWheelar("MH12CD5678");
        Vehicle truck = new Truck("MH12EF9012");

        ParkingTicket ticket1 = parkingLot.issueTicket(bike);
        ParkingTicket ticket2 = parkingLot.issueTicket(car);
        ParkingTicket ticket3 = parkingLot.issueTicket(truck);

        // Display updated availability
        System.out.println("\nAfter Parking Vehicles:");
        parkingLot.displayAllFloors();

        // Process exit with different payment modes
        System.out.println("\nProcessing Exits:");
        parkingLot.processExit(ticket1.getTicketId(), new CashPayment());
        parkingLot.processExit(ticket2.getTicketId(),
                new CardPayment());
        parkingLot.processExit(ticket3.getTicketId(),
                new UPIPayment());

        // Display final availability
        System.out.println("\nFinal Parking Availability:");
        parkingLot.displayAllFloors();


    }
}