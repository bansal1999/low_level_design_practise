package org.example;

import java.util.List;

public class EvaluationResult {

    private List<ExpenseResult> expenseResults;
    private List<TripResult> tripResults;

    public EvaluationResult(List<ExpenseResult> expenseResults, List<TripResult> tripResults) {
        this.expenseResults = expenseResults;
        this.tripResults = tripResults;
    }

    public List<ExpenseResult> getExpenseResults() {
        return expenseResults;
    }

    public List<TripResult> getTripResults() {
        return tripResults;
    }
}
