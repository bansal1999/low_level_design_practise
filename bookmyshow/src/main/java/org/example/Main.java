package org.example;

import org.example.Enums.SeatType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("BOOK MY SHOW");

        MovieTicketBookingSystem system = MovieTicketBookingSystem.getInstance();

        // Add movies
        Movie avengers = system.addMovie("Avengers: Endgame", "Epic conclusion to the Infinity Saga", 180);
        Movie joker = system.addMovie("Joker", "Psychological thriller about the iconic villain", 122);

        // Add theatres
        Theatre inox = system.addTheatre("Inox", "Delhi");
        Theatre pvr = system.addTheatre("PVR", "Pune");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //Add shows
        LocalDateTime now = LocalDateTime.now();
        Show show1 = system.addShow(avengers.getId(), inox.getId(), now.plusHours(1));
        Show show2 = system.addShow(avengers.getId(), inox.getId(), now.plusHours(2));
        Show show3 = system.addShow(avengers.getId(), inox.getId(), now.plusHours(3));

        // Add seats to the show
        for (char row = 'A'; row <= 'J'; row++) {
            for (int col = 1; col <= 10; col++) {
                SeatType type = (row >= 'H') ? SeatType.PREMIUM : SeatType.NORMAL;
                double price = (type == SeatType.PREMIUM) ? 300.0 : 200.0;
                system.addSeatToShow(show1.getId(), String.valueOf(row), col, type, price);
            }
        }

        // Add more seats for other shows
        // ...

        // Register users
        User user1 = system.addUser("John Doe", "john@example.com", "password123");
        User user2 = system.addUser("Jane Smith", "jane@example.com", "securepass");

        // User1 books tickets for Avengers at INOX
        System.out.println("Available shows for " + avengers.getTitle() + ":");
//        for (Show show : system.getShowsByMovie(avengers.getId())) {
//            System.out.println(show.getTheater().getName() + " at " + show.getStartTime().format(formatter));
//        }

        // Create a booking
        Booking booking1 = system.createBooking(user1.getId(), show1.getId());

//        // Add seats to the booking
//        boolean seat1Added = system.addSeatToBooking(booking1.getId(), show1.getSeats().values().iterator().next().getId());
//        if (seat1Added) {
//            System.out.println("Seat added to booking successfully");
//        } else {
//            System.out.println("Failed to add seat to booking");
//        }

        // Confirm the booking
        boolean bookingConfirmed = system.confirmBooking(booking1.getId(), "Credit Card");
        if (bookingConfirmed) {
            System.out.println("Booking confirmed successfully");
            System.out.println("Booking details: " + booking1);
        } else {
            System.out.println("Booking confirmation failed");
        }

//        // User2 tries to book the same seat (should fail)
//        Booking booking2 = system.createBooking(user2.getId(), show1.getId());
//        boolean seat2Added = system.addSeatToBooking(booking2.getId(), show1.getSeats().values().iterator().next().getId());
//        if (seat2Added) {
//            System.out.println("Seat added to booking successfully");
//        } else {
//            System.out.println("Failed to add seat to booking (already booked)");
//        }

        // Show all bookings for user1
        System.out.println("\nAll bookings for " + user1.getName() + ":");
        for (Booking booking : system.getBookingsByUser(user1.getId())) {
            System.out.println(booking);
        }

        // Cancel a booking
        boolean bookingCancelled = system.cancelBooking(booking1.getId());
        if (bookingCancelled) {
            System.out.println("\nBooking cancelled successfully");
            System.out.println("Updated booking status: " + system.getBooking(booking1.getId()).getBookingStatus());
        } else {
            System.out.println("\nBooking cancellation failed");
        }


    }
}