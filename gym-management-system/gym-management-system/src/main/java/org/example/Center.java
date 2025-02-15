package org.example;

import java.util.List;

public class Center {
    private String location;
    private List<WorkoutSlot> workoutSlots;

    public Center(String location, List<WorkoutSlot> workoutSlots) {
        this.location = location;
        this.workoutSlots = workoutSlots;
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
