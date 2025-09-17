package org.example;

abstract class BaseRule implements Rule{
    protected String ruleName;
    protected String violationMessage;

    public BaseRule(String ruleName, String violationMessage) {
        this.ruleName = ruleName;
        this.violationMessage = violationMessage;
    }

    @Override
    public String getRuleName() {
        return ruleName;
    }

    @Override
    public String getViolationMessage() {
        return violationMessage;
    }
}
