package org.example;

public interface Rule {
    String getRuleName();

    RuleType getRuleType();

    boolean evaluate(Object context);

    String getViolationMessage();

}
