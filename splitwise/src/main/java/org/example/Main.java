package org.example;

import org.example.enums.Splittype;
import org.example.model.Expense;
import org.example.model.Group;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.service.ExpenseService;
import org.example.service.SplitwiseService;
import org.example.split.Split;
import org.example.split.SplitFactory;

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

//        // Example 1: Equal Split - Dinner expense
        System.out.println("\n===== Example 1: Equal Split - Dinner =====");
        List<Split> splits1 = new ArrayList<>();
        splits1.add(SplitFactory.createEqualSplit(user1));
        splits1.add(SplitFactory.createEqualSplit(user2));
        splits1.add(SplitFactory.createEqualSplit(user3));

        Expense expense1 = splitwiseService.createExpense(
                group,
                300.0,
                "Dinner",
                user1,
                splits1,
                Splittype.EQUAL
        );

        System.out.println("Expense created: " + expense1.getDescription() + " for $" + expense1.getAmount());
        user1.printBalances();
        user2.printBalances();
        user3.printBalances();


//        // Example 2: Percentage Split - Hotel
        System.out.println("\n===== Example 2: Percentage Split - Hotel =====");
        List<Split> splits2 = new ArrayList<>();
        splits2.add(SplitFactory.createPercentSplit(user1, 40.0));
        splits2.add(SplitFactory.createPercentSplit(user2, 30.0));
        splits2.add(SplitFactory.createPercentSplit(user3, 30.0));

        Expense expense2 = splitwiseService.createExpense(
                group,
                1000.0,
                "Hotel",
                user2,
                splits2,
                Splittype.PERCENTAGE
        );

        System.out.println("Expense created: " + expense2.getDescription() + " for $" + expense2.getAmount());
        user1.printBalances();
        user2.printBalances();
        user3.printBalances();

//        // Example 3: Exact Split - Taxi
        System.out.println("\n===== Example 3: Exact Split - Taxi =====");
        List<Split> splits3 = new ArrayList<>();
        splits3.add(SplitFactory.creatExactSplit(user1, 50.0));
        splits3.add(SplitFactory.creatExactSplit(user2, 25.0));
        splits3.add(SplitFactory.creatExactSplit(user3, 25.0));

        Expense expense3 = splitwiseService.createExpense(
                group,
                100.0,
                "Taxi",
                user3,
                splits3,
                Splittype.EXACT
        );

        System.out.println("Expense created: " + expense3.getDescription() + " for $" + expense3.getAmount());
        user1.printBalances();
        user2.printBalances();
        user3.printBalances();

        // Settle up
        System.out.println("\n===== Settling Balances =====");
        // Alice settles with Charlie
        double aliceOwesCharlie = user1.getUserVsBalances().getOrDefault(user3.getId(), 0.0);
        if (aliceOwesCharlie > 0) {
            Transaction transaction = splitwiseService.settleBalance(user1, user3, aliceOwesCharlie);
            System.out.println("Settlement: " + transaction);
        }

        System.out.println("\n===== Final Balances =====");
        user1.printBalances();
        user2.printBalances();
        user3.printBalances();


    }
}