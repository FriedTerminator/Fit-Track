package entities;

import jakarta.persistence.ManyToOne;

public class Workout {
    private Long set;
    private Long reps;

    @ManyToOne
    private WorkoutExcercise workoutExcercise;

    public Long getSet() {
        return set;
    }

    public void setSet(Long set) {
        this.set = set;
    }

    public Long getReps() {
        return reps;
    }

    public void setReps(Long reps) {
        this.reps = reps;
    }


}
