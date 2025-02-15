package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSlot {

    private final String workoutType;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int capacity;
    private List<Booking> bookings;

    public WorkoutSlot(String workoutType, LocalTime startTime, LocalTime endTime, LocalDate endDate, LocalDate startDate, int capacity) {
        this.workoutType = workoutType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.startDate = startDate;
        this.capacity = capacity;
        this.bookings = new ArrayList<>();
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean isAvailable(LocalDate date) {
        return date.isEqual(startDate)
                || (date.isAfter(startDate) && date.isBefore(endDate))
                || date.isEqual(endDate);
    }

    public int getAvailableSeats(LocalDate date) {
        if (!isAvailable(date))
            return 0;

        long bookedSeats = bookings.stream()
                .filter(booking -> booking.getDate().equals(date))
                .count();
        return (int) (capacity - bookedSeats);
    }
}
