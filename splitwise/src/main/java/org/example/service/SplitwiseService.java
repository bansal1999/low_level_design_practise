package org.example.service;

import org.example.enums.Splittype;
import org.example.model.Expense;
import org.example.model.Group;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.split.PercentSplit;
import org.example.split.Split;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitwiseService implements ExpenseService {
    private static SplitwiseService instance;
    private ConcurrentHashMap<String, User> users;
    private ConcurrentHashMap<String, Group> groups;
    private List<Transaction> transactions;
    private AtomicInteger userIdCounter;
    private AtomicInteger groupIdCounter;
    private AtomicInteger expenseIdCounter;
    private AtomicInteger transactionIdCounter;

    private SplitwiseService() {
        users = new ConcurrentHashMap<>();
        groups = new ConcurrentHashMap<>();
        transactions = new CopyOnWriteArrayList<>();
        groupIdCounter = new AtomicInteger(0);
        userIdCounter = new AtomicInteger(0);
        expenseIdCounter = new AtomicInteger(0);
        transactionIdCounter = new AtomicInteger(0);
    }

    public static synchronized SplitwiseService getInstance() {
        if (instance == null) {
            instance = new SplitwiseService();
        }
        return instance;
    }

    // USER MANAGEMENT
    @Override
    public User createUser(String name, String email) {
        String userId = "u" + userIdCounter.incrementAndGet();
        User user = new User(userId, name, email);
        users.put(userId, user);
        return user;
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    // GROUP MANAGEMENT
    @Override
    public Group createGroup(String name, List<User> members) {
        String groupId = "g" + groupIdCounter.incrementAndGet();
        Group group = new Group(groupId, name);

        for (User member : members) {
            group.addMember(member);
        }

        groups.put(groupId, group);
        return group;
    }

    @Override
    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }

    @Override
    public void addUserToGroup(String groupId, User user) {
        Group group = groups.get(groupId);
        if (group != null) {
            group.addMember(user);
        }
    }

    // EXPENSE MANAGEMENT
    @Override
    public Expense createExpense(Group group, double amount, String description, User paidBy, List<Split> splits, Splittype splittype) {
        String expenseId = "e" + expenseIdCounter.incrementAndGet();

        switch (splittype) {
            case EQUAL:
                validateEqualSplit(splits, amount);
                break;
            case PERCENTAGE:
                validatePercentageSplit(splits, amount);
                break;
            case EXACT:
                validateExactSplit(splits, amount);
                break;
        }

        Expense expense = new Expense(expenseId, amount, description, paidBy, splits, splittype, new Date());
        group.addExpense(expense);

        //update balance
        for (Split split : splits) {
            User payee = split.getUser();
            double share = split.getAmount();

            if (!payee.getId().equals(paidBy.getId())) {
                payee.updateBalance(paidBy.getId(), share);
                paidBy.updateBalance(payee.getId(), -share);
            }
        }
        return expense;
    }

    private void validateEqualSplit(List<Split> splits, double amount) {
        double splitAmount = amount / splits.size();
        for (Split split : splits) {
            split.setAmount(splitAmount);
        }
    }

    private void validatePercentageSplit(List<Split> splits, double amount) {
        double totalPercentage = 0;
        for (Split split : splits) {
            if (split instanceof PercentSplit) {
                PercentSplit percentSplit = (PercentSplit) split;
                totalPercentage += percentSplit.getPercentage();
            }
        }
        if (Math.abs(totalPercentage - 100.0) > 0.001) {
            throw new IllegalArgumentException("Total percentage should be 100%");
        }
        for (Split split : splits) {
            if (split instanceof PercentSplit) {
                PercentSplit percentSplit = (PercentSplit) split;
                split.setAmount((percentSplit.getPercentage() / 100) * amount);
            }
        }
    }

    private void validateExactSplit(List<Split> splits, double amount) {
        double totalAmount = 0;
        for (Split split : splits) {
            totalAmount += split.getAmount();
        }
        if (Math.abs(totalAmount - amount) > 0.001) {
            throw new IllegalArgumentException("Total amount should match the expense amount");
        }
    }


    @Override
    public Transaction settleBalance(User from, User to, double amount) {
        String transactionId = "t" + transactionIdCounter.incrementAndGet();
        //update balance
        from.updateBalance(from.getId(), -amount);
        to.updateBalance(to.getId(), amount);

        Transaction transaction = new Transaction(transactionId, from, to, amount);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public List<Expense> getUserExpenses(String userId) {
        List<Expense> userExpenses = new ArrayList<>();

        for (Group group : groups.values()) {
            for (Expense expense : group.getExpenses()) {
                if (expense.getPaidBy().getId().equals(userId)) {
                    userExpenses.add(expense);
                } else {
                    for (Split split : expense.getSplits()) {
                        if (split.getUser().getId().equals(userId)) {
                            userExpenses.add(expense);
                            break;
                        }
                    }
                }
            }
        }
        return userExpenses;
    }

    @Override
    public List<Transaction> getUserTransaction(String userId) {
        List<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getFrom().getId().equals(userId) ||
                    transaction.getTo().getId().equals(userId)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }
}
