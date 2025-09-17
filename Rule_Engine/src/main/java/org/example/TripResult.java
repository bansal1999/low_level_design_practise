package org.example;

import java.util.ArrayList;
import java.util.List;

public class TripResult {
    private String tripId;
    private List<String> violations;

    public TripResult(String tripId, List<String> violations) {
        this.tripId = tripId;
        this.violations = violations != null ? violations : new ArrayList<>();
    }

    public String getTripId() {
        return tripId;
    }

    public List<String> getViolations() {
        return violations;
    }

    public boolean hasViolations() {
        return !violations.isEmpty();
    }
}
