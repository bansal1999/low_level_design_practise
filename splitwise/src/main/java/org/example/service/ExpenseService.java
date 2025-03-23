package org.example.service;

import org.example.enums.Splittype;
import org.example.model.Expense;
import org.example.model.Group;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.split.Split;

import java.util.List;

public interface ExpenseService {
    User createUser(String name, String email);

    User getUser(String userId);

    Group createGroup(String name, List<User> members);

    Group getGroup(String groupId);

    void addUserToGroup(String groupId, User user);

    Expense createExpense(Group group, double amount, String description, User paidBy, List<Split> splits, Splittype splittype);

    Transaction settleBalance(User from, User to, double amount);

    List<Expense> getUserExpenses(String userId);

    List<Transaction> getUserTransaction(String userId);

}
