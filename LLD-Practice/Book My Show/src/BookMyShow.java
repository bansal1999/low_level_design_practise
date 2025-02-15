import java.util.List;
import java.util.Map;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;

    public BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        //user 1
        bookMyShow.createBooking(City.DELHI, "AVENGERS");
        //user 2
        bookMyShow.createBooking(City.JAIPUR, "IRON MAN");

    }

    private void createBooking(City userCity, String movieName) {

        //1. search movie by an location
        List<Movie> movies = movieController.getMoviesByCity(userCity);

        //2. find the movie from the list which user wants to see
        Movie interestedMovie = null;
        for(Movie movie : movies){
            if(movie.getTitle().equalsIgnoreCase(movieName)){
                interestedMovie = movie;
                break;
            }
        }

        //3. get all shows of thjs movies in banglore location
        Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShows(interestedMovie, userCity);

        //4. select particular show user is interested
        Map.Entry<Theatre, List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
        List<Show> runningShows = entry.getValue();
        Show interestedShow = runningShows.get(0);

        //5. select the seat
        int seatNumber = 40;
        List<Integer> bookedSeats = interestedShow.getBookedSeatIds();
        if(!bookedSeats.contains(seatNumber)){
            bookedSeats.add(seatNumber);
            //start payment
            Booking booking = new Booking();

        }

    }


    private void initialize() {
        createMovie();
        createTheatre();
    }

    private void createMovie() {

    }

    private void createTheatre() {

    }
}