package org.example;

import org.example.Enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private double totalPrice;
    private List<Seat> selectedSeats;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;

    public Booking(String id, User user, Show show) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.selectedSeats = new ArrayList<>();
        this.totalPrice = 0.0;
        this.bookingStatus = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    //methods
    public void addSeat(Seat seat) {
        selectedSeats.add(seat);
        totalPrice += seat.getPrice();
    }

    public void removeSeat(Seat seat) {
        selectedSeats.remove(seat);
        totalPrice -= seat.getPrice();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", show=" + show +
                ", totalPrice=" + totalPrice +
                ", selectedSeats=" + selectedSeats +
                ", bookingTime=" + bookingTime +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}
