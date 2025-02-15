package org.example;

import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gym Management System");
        FitnessService service = new FitnessService();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");

        try {
            //Register User
            service.registerUser("Johan Doe", "johan123@gmail.com", "Koramangla");
            service.registerUser("Jane Smith", "jane567@gmail.com", "Bellendur");


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}