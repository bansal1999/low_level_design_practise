package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExpenseResult {
    private String expenseId;
    private boolean approved;
    private List<String> violations;

    public ExpenseResult(String expenseId, boolean approved, List<String> violations) {
        this.expenseId = expenseId;
        this.approved = approved;
        this.violations = violations != null ? violations : new ArrayList<>();
    }

    public String getExpenseId() {
        return expenseId;
    }

    public boolean isApproved() {
        return approved;
    }

    public List<String> getViolations() {
        return violations;
    }
}
