package org.example;

import java.util.HashMap;
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

    public void registerUser(String name, String email, String location){
        if(users.containsKey(email)){
            throw new IllegalArgumentException("User already registered");
        }
        users.put(email, new User(name, email, location));
    }
}
