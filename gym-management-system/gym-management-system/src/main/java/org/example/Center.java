package org.example;

import java.util.ArrayList;
import java.util.List;

public class Center {
    private final String location;
    private List<WorkoutSlot> workoutSlots;

    public Center(String location) {
        this.location = location;
        this.workoutSlots = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public List<WorkoutSlot> getWorkoutSlots() {
        return workoutSlots;
    }

    public void addWorkoutSlot(WorkoutSlot workoutSlot) {
        workoutSlots.add(workoutSlot);
    }
}
