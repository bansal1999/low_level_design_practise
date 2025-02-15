package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();

        //Add car to rental system
        rentalSystem.addCar(new Car("Toyota", "Camry", 2022, "ABC123", 50.0));
        rentalSystem.addCar(new Car("Honda", "City", 2021, "ACB132", 30.0));
        rentalSystem.addCar(new Car("Ford", "Figo", 2023, "XYZ123", 20.0));

        //CREATE CUSTOMERS
        Customer customer1 = new Customer("John Doe", "john@example.com", "DL1234");

        //make reservation
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        List<Car> availableCarList = rentalSystem.searchCars("Ford", "Figo", startDate, endDate);
        if (!availableCarList.isEmpty()) {
            Car selectedCar = availableCarList.get(0);
            Reservation reservation = rentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);
            if (reservation != null) {
                boolean paymentProcess = rentalSystem.processPayment(reservation);
                if (paymentProcess) {
                    System.out.println("Reservation successful" + reservation.getReservationId());
                } else {
                    System.out.println("Failed to process payment for reservation " + reservation.getReservationId());
                    rentalSystem.cancelReservation(reservation.getReservationId());
                }
            } else {
                System.out.println("No available cars for the selected date");
            }

        } else {
            System.out.println("No available cars for the given make and model");
        }
    }
}