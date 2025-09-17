package org.example;

public class NoAirFareRule extends BaseRule{

    public NoAirFareRule() {
        super("No Airfare", "Airfare expenses not allowed");
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.EXPENSE_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        Expense expense = (Expense)context;
        return !"airfare".equalsIgnoreCase(expense.getExpenseType());
    }
}
