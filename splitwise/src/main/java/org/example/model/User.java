package org.example.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {

    private String id;
    private String name;
    private String email;
    private ConcurrentHashMap<String, Double> userVsBalances;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userVsBalances = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ConcurrentHashMap<String, Double> getUserVsBalances() {
        return userVsBalances;
    }

    public void updateBalance(String userId, double amount) {
        userVsBalances.compute(userId, (key, value) -> (value == null ? 0 : value) + amount);

        if (userVsBalances.get(userId) == 0) {
            userVsBalances.remove(userId);
        }
    }

    public void printBalances() {
        System.out.println("Balances for " + name + ":");
        if (userVsBalances.isEmpty()) {
            System.out.println("No balances found.");
            return;
        }
        for (Map.Entry<String, Double> entry : userVsBalances.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println("Owes $" + entry.getValue() + " to user with id " + entry.getKey());
            } else {
                System.out.println("Is Owed $" + Math.abs(entry.getValue()) + " to user with id " + entry.getKey());
            }
        }
    }
}
