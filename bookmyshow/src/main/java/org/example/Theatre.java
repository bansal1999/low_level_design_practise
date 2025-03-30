package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Theatre {

    private String id;
    private String name;
    private String location;
    private ConcurrentHashMap<String, Show> shows;

    public Theatre(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.shows = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ConcurrentHashMap<String, Show> getShows() {
        return shows;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Show> getShowsByMovie(String movieId) {
        List<Show> movieShows = new ArrayList<>();

        for (Show show : shows.values()) {
            if (show.getMovie().getId().equals(movieId)) {
                movieShows.add(show);
            }
        }
        return movieShows;
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public void removeShow(String id) {
        shows.remove(id);
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", shows=" + shows.size() +
                '}';
    }
}
