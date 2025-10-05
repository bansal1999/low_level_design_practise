package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> floors;
    private Map<String, ParkingTicket> activeTickets;
    private PricingStrategy pricingStrategy;
    private int ticketCounter;

    public ParkingLot() {
        this.floors = new ArrayList<>();
        activeTickets = new HashMap<>();
        pricingStrategy = new PricingStrategy();
        ticketCounter = 0;
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableParkingSpot(vehicle.getVehicleType());
            if (spot != null) {
                spot.parkVehicle(vehicle);
                String ticketId = "TKT" + (++ticketCounter);
                ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, spot);
                activeTickets.put(ticketId, ticket);
                System.out.println("Ticket issued: " + ticketId +
                        " | Floor: " + floor.getFloorNumber() +
                        " | Spot: " + spot.getSpotId());
                return ticket;
            }
        }
        System.out.println("No available parking spot for the vehicle type: " + vehicle.getVehicleType());
        return null;
    }

    public double processExit(String ticketId, PaymentStrategy paymentStrategy) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid Ticket ID ");
            return 0.0;

        }
        ticket.setExitTime(System.currentTimeMillis());
        long hours = ticket.getDurationInHours();
        double amount = this.pricingStrategy.calculatePrice(
                ticket.getVehicle().getVehicleType(), hours);
        ticket.setAmount(amount);

        if (paymentStrategy.processPayment(amount)) {
            ticket.getParkingSpot().removeVehicle();
            activeTickets.remove(ticketId);
            System.out.println("Exit processed for ticket: " + ticketId +
                    " | Amount: $" + amount);
            return amount;
        }
        return 0;
    }

    public void displayAllFloors(){
        for(ParkingFloor floor: floors){
            DisplayBoard board = new DisplayBoard(floor.getFloorNumber());
            board.displayAvailability(floor);
        }
    }


}
