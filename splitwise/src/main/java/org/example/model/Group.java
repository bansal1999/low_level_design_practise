package org.example.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Group {

    private String id;
    private String name;
    private List<User> users;
    private List<Expense> expenses;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.users = new CopyOnWriteArrayList<>();
        this.expenses = new CopyOnWriteArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addMember(User member) {
        users.add(member);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
