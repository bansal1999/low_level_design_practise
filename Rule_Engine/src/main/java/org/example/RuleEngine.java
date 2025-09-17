package org.example;

import java.util.Arrays;
import java.util.List;

public class RuleEngine {
    private List<Rule> expenseRules;
    private List<Rule> tripRules;

    public RuleEngine() {
        initializeDefaultValues();
    }

    private void initializeDefaultValues() {
        expenseRules = Arrays.asList(
                new RestaurantExpenseLimitRule(75),
                new NoAirFareRule(),
                new NoEntertainmentRule(),
                new SingleExpenseLimitRule(250)
        );

        tripRules = Arrays.asList(
                new TripMealLimitRule(2000),
                new TripTotalLimitRule(1000)
        );
    }


}
