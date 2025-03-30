package org.example;

import org.example.Enums.SeatStatus;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class Show {
    private final String id;
    private Movie movie;
    private Theatre theatre;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ConcurrentHashMap<String, Seat> seats;

    public Show(String id, Movie movie, Theatre theatre, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration());
        this.seats = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ConcurrentHashMap<String, Seat> getSeats() {
        return seats;
    }

    //Methods
    public void addSeats(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    public boolean isSeatAvailable(String seatId) {
        Seat seat = seats.get(seatId);
        return seat != null && seat.getStatus() == SeatStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", movie=" + movie +
                ", theatre=" + theatre +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", seats=" + seats.size() +
                '}';
    }
}
