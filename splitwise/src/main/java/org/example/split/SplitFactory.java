package org.example.split;

import org.example.model.User;

public class SplitFactory {
    public static Split createEqualSplit(User user) {
        return new EqualSplit(user);
    }

    public static Split createPercentSplit(User user, double percentage) {
        return new PercentSplit(user, percentage);
    }

    public static Split creatExactSplit(User user, double amount) {
        return new ExactSplit(user, amount);
    }
}
