import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Theatre>> cityVsTheatre;
    List<Theatre> allTheatres;

    public TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatres = new ArrayList<>();
    }

    void addTheatre(Theatre theatre, City city) {
        allTheatres.add(theatre);
        List<Theatre> newTheatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        newTheatres.add(theatre);
        cityVsTheatre.put(city, newTheatres);
    }

    Map<Theatre, List<Show>> getAllShows(Movie movie, City city) {
        //get all theatres of this city
        Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();
        List<Theatre> theatres = cityVsTheatre.get(city);

        for (Theatre theatre : theatres) {
            List<Show> givenMovieShows = new ArrayList<>();
            List<Show> shows = theatre.getShows();

            for (Show show : shows) {
                if (show.getMovie().equals(movie)) {
                    givenMovieShows.add(show);
                }
                if (!givenMovieShows.isEmpty()) {
                    theatreVsShows.put(theatre, givenMovieShows);
                }
            }
        }
        return theatreVsShows;

    }

}
