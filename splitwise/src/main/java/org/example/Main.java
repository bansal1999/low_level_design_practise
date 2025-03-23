package org.example;

import org.example.model.Group;
import org.example.model.User;
import org.example.service.ExpenseService;
import org.example.service.SplitwiseService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("SplitWise System Design");

        // Create singleton instance
        ExpenseService splitwiseService = SplitwiseService.getInstance();

        //Create users
        User user1 = splitwiseService.createUser("Alice", "alice@example.com");
        User user2 = splitwiseService.createUser("Bob", "bob@example.com");
        User user3 = splitwiseService.createUser("Charlie", "charlie@example.com");

        // Create group
        List<User> members = new ArrayList<>();
        members.add(user1);
        members.add(user2);
        members.add(user3);
        Group group = splitwiseService.createGroup("Trip to mountains", members);

    }
}