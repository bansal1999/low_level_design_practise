package org.example;

import org.example.Enums.BookingStatus;
import org.example.Enums.SeatStatus;
import org.example.Enums.SeatType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MovieTicketBookingSystem {

    //Singleton instance
    private static MovieTicketBookingSystem instance;
    private static final ReentrantLock lock = new ReentrantLock();

    //data stores
    private ConcurrentHashMap<String, Movie> movies;
    private ConcurrentHashMap<String, Theatre> theatres;
    private ConcurrentHashMap<String, User> users;
    private ConcurrentHashMap<String, Show> shows;
    private ConcurrentHashMap<String, Booking> bookings;

    public MovieTicketBookingSystem() {
        movies = new ConcurrentHashMap<String, Movie>();
        theatres = new ConcurrentHashMap<String, Theatre>();
        users = new ConcurrentHashMap<String, User>();
        shows = new ConcurrentHashMap<String, Show>();
        bookings = new ConcurrentHashMap<String, Booking>();
    }

    //singleton getinstance
    public static MovieTicketBookingSystem getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new MovieTicketBookingSystem();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    //Movie Management
    public Movie addMovie(String title, String description, int duration) {
        String id = UUID.randomUUID().toString();
        Movie movie = new Movie(id, title, description, duration);
        movies.put(id, movie);
        return movie;
    }

    public void updateMovie(String id, String title, String description, int duration) {
        Movie movie = movies.get(id);
        if (movie != null) {
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setDuration(duration);
        }
    }

    public void deleteMovie(String id) {
        movies.remove(id);
    }

    public Movie getMovie(String id) {
        return movies.get(id);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }

    // Theatre Management
    public Theatre addTheatre(String name, String location) {
        String id = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(id, name, location);
        theatres.put(id, theatre);
        return theatre;
    }

    public void updateTheatre(String id, String name, String location) {
        Theatre theatre = theatres.get(id);
        if (theatre != null) {
            theatre.setName(name);
            theatre.setLocation(location);
        }
    }

    public void deleteTheatre(String id) {
        theatres.remove(id);
    }

    public Theatre getTheatre(String id) {
        return theatres.get(id);
    }

    public List<Theatre> getAllTheatres() {
        return new ArrayList<>(theatres.values());
    }

    // User Management
    public User addUser(String name, String email, String password) {
        String id = UUID.randomUUID().toString();
        User user = new User(name, email, password, id);
        users.put(id, user);
        return user;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // Show Management
    public Show addShow(String movieId, String theatreId, LocalDateTime startTime) {
        Movie movie = movies.get(movieId);
        Theatre theatre = theatres.get(theatreId);

        if (movie == null || theatre == null) {
            return null;
        }

        String id = UUID.randomUUID().toString();
        Show show = new Show(id, movie, theatre, startTime);
        shows.put(id, show);
        theatre.addShow(show);
        return show;
    }

    public void removeShow(String id) {
        Show show = shows.get(id);
        if (show != null) {
            show.getTheatre().removeShow(id);
            shows.remove(id);
        }
    }

    public Show getShow(String id) {
        return shows.get(id);
    }

    //Seat Manager
    public Seat addSeatToShow(String showId, String row, int column, SeatType seatType, double price) {
        Show show = shows.get(showId);
        if (show != null) {
            String id = UUID.randomUUID().toString();
            Seat seat = new Seat(id, row, column, price, seatType);
            show.addSeats(seat);
            return seat;
        }
        return null;
    }

    //BOOKING management
    public Booking createBooking(String userId, String showId) {
        User user = users.get(userId);
        Show show = shows.get(showId);

        if (user == null || show == null) {
            return null;
        }

        String id = UUID.randomUUID().toString();
        Booking booking = new Booking(id, user, show);
        bookings.put(id, booking);
        return booking;
    }

    public boolean addSeatToBooking(String bookingId, String seatId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || booking.getBookingStatus() != BookingStatus.PENDING) {
            return false;
        }

        Show show = booking.getShow();
        Seat seat = show.getSeat(seatId);

        if (seat == null || seat.getStatus() != SeatStatus.AVAILABLE) {
            return false;
        }

        synchronized (seat) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                return false;
            }
            seat.setStatus(SeatStatus.BOOKED);
            booking.addSeat(seat);
        }

        return true;
    }

    public boolean confirmBooking(String bookingId, String paymentMethod) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || booking.getBookingStatus() != BookingStatus.PENDING) {
            return false;
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return true;
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || booking.getBookingStatus() != BookingStatus.PENDING) {
            return false;
        }

        for (Seat seat : booking.getSelectedSeats()) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);
        return true;
    }

    public Booking getBooking(String id) {
        return bookings.get(id);
    }

    public List<Booking> getBookingsByUser(String userId) {
        List<Booking> userBooking = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getUser().getId().equals(userId)) {
                userBooking.add(booking);
            }
        }
        return userBooking;
    }


}
