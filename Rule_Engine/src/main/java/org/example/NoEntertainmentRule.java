package org.example;

public class NoEntertainmentRule extends BaseRule {

    public NoEntertainmentRule() {
        super("No Entertainment rule", "Entertainment expenses not allowed");
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.EXPENSE_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        Expense expense = (Expense) context;
        return !"entertainment".equalsIgnoreCase(expense.getExpenseType());
    }
}
