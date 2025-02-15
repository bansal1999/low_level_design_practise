package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String email;
    private final String location;
    private List<Booking> bookings;

    public User(String name, String email, String location) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.bookings = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
