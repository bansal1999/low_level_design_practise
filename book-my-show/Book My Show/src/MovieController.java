import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City, List<Movie>> cityVsMovies;
    List<Movie> allMovies;

    public MovieController() {
        cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    void addMovie(Movie movie, City city) {
        allMovies.add(movie);
        List<Movie> newMovies = cityVsMovies.getOrDefault(city, new ArrayList<>());
        newMovies.add(movie);
        cityVsMovies.put(city, newMovies);
    }

    Movie getMovieByName(String movieName) {
        for (Movie movie : allMovies) {
            if (movie.getTitle().equals(movieName)) {
                return movie;
            }
        }
        return null;

    }

    List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.getOrDefault(city, new ArrayList<>());
    }
}
