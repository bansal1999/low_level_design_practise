package org.example;

public class TripMealLimitRule extends BaseRule {

    private double maxAmount;

    public TripMealLimitRule(double maxAmount) {
        super("Trip meal limit", "Total meal expenses per trip exceed ");
        this.maxAmount = maxAmount;
    }


    @Override
    public RuleType getRuleType() {
        return RuleType.TRIP_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        TripContext tripContext = (TripContext) context;
        double mealTotal = tripContext.getExpenses().stream()
                .filter(e -> "restautant".equalsIgnoreCase(e.getExpenseType()))
                .mapToDouble(Expense::getAmountUsd)
                .sum();

        return mealTotal <= maxAmount;

    }
}
