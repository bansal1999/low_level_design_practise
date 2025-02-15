package org.example;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Station {
    private final String stationId;
    private final String name;
    private int sequenceNumber; // position in train route

    public Station(String stationId, String name, int sequenceNumber) {
        this.stationId = stationId;
        this.name = name;
        this.sequenceNumber = sequenceNumber;
    }

    //getters
    public String getStationId() {
        return stationId;
    }

    public String getName() {
        return name;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
}

class Train {
    private final String trainNumber;
    private List<Station> route;
    private final int totalSeats;
    private final String name;

    public Train(String trainNumber, int totalSeats, String name) {
        this.trainNumber = trainNumber;
        this.totalSeats = totalSeats;
        this.name = name;
        this.route = new ArrayList<>();
    }

    public void addStation(Station station) {
        route.add(station);
    }

    //getter
    public List<Station> getRoute() {
        return route;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}

class Booking {
    private final String bookingId;
    private final String trainNumber;
    private String source;
    private String destination;
    private LocalDate travalDate;
    private int seatNumber;
    private String userId;

    //constructor
    public Booking(String bookingId, String trainNumber, String source, String destination, LocalDate travalDate, int seatNumber, String userId) {
        this.bookingId = bookingId;
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.travalDate = travalDate;
        this.seatNumber = seatNumber;
        this.userId = userId;
    }

    //getters
    public String getBookingId() {
        return bookingId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getTravalDate() {
        return travalDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}

class BookingManager {
    private final Map<String, Train> trains;
    private final Map<String, List<Booking>> bookings;
    private final Object lockObject = new Object();

    //constructor
    public BookingManager() {
        this.trains = new HashMap<>();
        this.bookings = new HashMap<>();
    }

    public void addTrain(Train train) {
        trains.put(train.getTrainNumber(), train);
        bookings.put(train.getTrainNumber(), new ArrayList<>());
    }

    public List<Train> searchTrain(String source, String destination, LocalDate travalDate) {
        return trains.values().stream()
                .filter(train -> isTrainAvailable(train, source, destination))
                .collect(Collectors.toList());
    }

    private boolean isTrainAvailable(Train train, String source, String destination) {
        List<Station> route = train.getRoute();
        int sourceIndex = -1;
        int destIndex = -1;

        for (int i = 0; i < route.size(); i++) {
            if (route.get(i).getStationId().equals(source))
                sourceIndex = i;
            if (route.get(i).getStationId().equals(destination))
                destIndex = i;
        }
        return sourceIndex != -1 && destIndex != -1 && sourceIndex < destIndex;
    }

    public Booking bookTicket(String trainNumber, String source, String destination, LocalDate date, String userId)
            throws Exception {
        synchronized (lockObject){
            Train train = trains.get(trainNumber);
            if(train == null){
                throw new Exception("No train found with given number.");
            }
            int availableSeats = getAvailableSeats(trainNumber, source, destination, date);
            if(availableSeats == 0){
                throw  new Exception("No seats available");
            }
            int seatNumber = findFirsAvailableSeat(train, source, destination, date);
            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, trainNumber, source, destination, date, seatNumber, userId);
            bookings.get(trainNumber).add(booking);
            return booking;
        }


    }

    public void cancelTicket(String bookingId) throws Exception {
        for (List<Booking> trainBookings : bookings.values()) {
            for (Iterator<Booking> iterator = trainBookings.iterator(); iterator.hasNext(); ) {
                Booking booking = iterator.next();
                if (booking.getBookingId().equals(booking)) {
                    iterator.remove();
                    System.out.println("Ticket cancelled successfully.");
                    return;
                }
            }
        }
        throw new Exception("No booking found with given ID.");
    }


}


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}