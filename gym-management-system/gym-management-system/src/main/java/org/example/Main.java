package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gym Management System");
        FitnessService service = new FitnessService();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");

        try {
            //Register User
            service.registerUser("John Doe", "john123@gmail.com", "Koramangla");
            service.registerUser("Jane Smith", "jane567@gmail.com", "Bellendur");

            // Add workouts
            LocalDate startDate = LocalDate.parse("01-09-24", dateFormatter);
            LocalDate endDate = LocalDate.parse("30-09-24", dateFormatter);

            // Add morning slots
            service.addWorkout("Koramangla", "Weights",
                    LocalTime.of(6, 0), LocalTime.of(7, 0),
                    10, startDate, endDate);

            service.addWorkout("Koramangla", "Yoga",
                    LocalTime.of(7, 0), LocalTime.of(8, 0),
                    15, startDate, endDate);

            // Book sessions
            LocalDate bookingDate = LocalDate.parse("20-09-24", dateFormatter);
            service.bookSession("john123@gmail.com", "Koramangla", "Weights",
                    LocalTime.of(6, 0), LocalTime.of(7, 0), bookingDate);

            // View schedule
            System.out.println("John's schedule for " + bookingDate + ":");
            service.viewSchedule("john123@gmail.com", bookingDate)
                    .forEach(booking -> System.out.println(
                            booking.getWorkoutSlot().getWorkoutType() + " at " +
                                    booking.getWorkoutSlot().getStartTime()
                    ));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}