import java.util.ArrayList;
import java.util.List;

public class Theatre {
    int theatreId;
    String address;
    City city;
    List<Show> shows = new ArrayList<Show>();
    List<Screen> screens = new ArrayList<Screen>();

    public int getTheatreId() {
        return theatreId;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public List<Show> getShows() {
        return shows;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}
