package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, User> users;
    private int userCounter = 1;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public User registerUser(String username, String email, String password, String address) {
        String userId = "U" + String.format("03d", userCounter++);
        User user = new User(userId, username, email, password, address);
        users.put(userId, user);
        return user;
    }

    public User getUser(String userId){
        return users.get(userId);
    }




}
