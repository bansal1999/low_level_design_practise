package org.example;

public class RestaurantExpenseLimitRule extends BaseRule {
    private double maxAmount;

    public RestaurantExpenseLimitRule(double maxAmount) {
        super("Restaurant Expense Limit", "Restaurant expense exceeds " + (int) maxAmount);
        this.maxAmount = maxAmount;
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.EXPENSE_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        Expense expense = (Expense) context;
        return !("restaurant".equalsIgnoreCase(expense.getExpenseType()) &&
                expense.getAmountUsd() > maxAmount);
    }
}
