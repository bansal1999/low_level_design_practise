package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessService {
    private Map<String, User> users;
    private Map<String, Center> centers;

    private static final int MAX_WEEKLY_BOOKINGS = 5;

    public FitnessService() {
        this.users = new HashMap<>();
        this.centers = new HashMap<>();
        //initialise default centres
        centers.put("Bellendur", new Center("Bellendur"));
        centers.put("Koramangla", new Center("Koramangla"));
    }

    public void registerUser(String name, String email, String location) {
        if (users.containsKey(email)) {
            throw new IllegalArgumentException("User already registered");
        }
        users.put(email, new User(name, email, location));
    }

    public List<WorkoutSlot> viewWorkoutSlotAvailability(String workoutType, LocalDate date) {
        List<WorkoutSlot> slotsAvailable = new ArrayList<>();

        for (Center center : centers.values()) {
            for (WorkoutSlot slot : center.getWorkoutSlots()) {
                if (slot.getWorkoutType().equals(workoutType) &&
                        slot.isAvailable(date) &&
                        slot.getAvailableSeats(date) > 0) {
                    slotsAvailable.add(slot);
                }
            }
        }
        return slotsAvailable;
    }

    public void bookSession(String email, String centerLocation, String workoutType, LocalTime startTime, LocalTime endTime, LocalDate date) {
        User user = users.get(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Center center = centers.get(centerLocation);
        if (center == null) {
            throw new IllegalArgumentException("Center not found");
        }

        //TODO: check weekly booking limit
        if (exceedsWeeklyBookingLimit(user, date)) {
            throw new IllegalStateException("Weekly booking limit exceeded");
        }


        //fIND MATCHING workout slot
        WorkoutSlot targetSlot = null;
        for (WorkoutSlot slot : center.getWorkoutSlots()) {
            if (slot.getWorkoutType().equals(workoutType) &&
                    slot.getStartTime().equals(startTime) &&
                    slot.getEndTime().equals(endTime) &&
                    slot.isAvailable(date)) {
                targetSlot = slot;
                break;
            }
        }

        if (targetSlot == null || targetSlot.getAvailableSeats(date) <= 0) {
            throw new IllegalArgumentException("No avaialble slots");
        }

        //create booking
        Booking booking = new Booking(user, targetSlot, date);
        user.addBooking(booking);
        targetSlot.getBookings().add(booking);

    }

    //todo: exceedWeeklyBookingsLimit()
    private boolean exceedsWeeklyBookingLimit(User user, LocalDate bookingDate) {
        LocalDate weekStart = bookingDate.minusDays(bookingDate.getDayOfWeek().getValue() - 1);
        LocalDate weekEnd = weekStart.plusDays(6);

        long weeklyBookings = user.getBookings().stream()
                .filter(booking ->
                        !booking.getDate().isBefore(weekStart) &&
                                !booking.getDate().isAfter(weekEnd))
                .count();

        return weeklyBookings >= MAX_WEEKLY_BOOKINGS;
    }

    //View schedule of a user
    public List<Booking> viewSchedule(String email, LocalDate date) {
        User user = users.get(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : user.getBookings()) {
            if (booking.getDate().equals(date)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public void addWorkout(String centerLocation, String workoutType, LocalTime startTime, LocalTime endTime,
                           int capacity, LocalDate startDate, LocalDate endDate) {

        Center center = centers.get(centerLocation);
        if (center == null) {
            throw new IllegalArgumentException("Center not found");
        }

        WorkoutSlot newSlot = new WorkoutSlot(workoutType, startTime, endTime, endDate, startDate, capacity);
        center.addWorkoutSlot(newSlot);


    }


}
