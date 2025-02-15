package org.example;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
    private User user;
    private WorkoutSlot workoutSlot;
    private LocalDate date;

    public Booking(User user, WorkoutSlot workoutSlot, LocalDate date) {
        this.user = user;
        this.workoutSlot = workoutSlot;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public WorkoutSlot getWorkoutSlot() {
        return workoutSlot;
    }

    public LocalDate getDate() {
        return date;
    }
}
