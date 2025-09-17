package org.example;

public class TripTotalLimitRule extends BaseRule {
    private double maxAmount;

    public TripTotalLimitRule(double maxAmount) {
        super("Trip total limit", "Total total per trip exceeded ");
        this.maxAmount = maxAmount;
    }


    @Override
    public RuleType getRuleType() {
        return RuleType.TRIP_LEVEL;
    }

    @Override
    public boolean evaluate(Object context) {
        TripContext tripContext = (TripContext) context;
        return tripContext.getTotalAmount() <= maxAmount;

    }
}
