package org.example;

public class SingleExpenseLimitRule extends BaseRule {

    private double maxAmount;

    public SingleExpenseLimitRule(double maxAmount) {
        super("Single Expense Limit", "Expense Exceeds " + (int) maxAmount);
        this.maxAmount = maxAmount;
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.EXPENSE_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        Expense expense = (Expense) context;
        return expense.getAmountUsd() <= maxAmount;
    }
}
