package entities;

import jakarta.persistence.ManyToOne;

public class Workout {
    private Long set;
    private Long reps;

    @ManyToOne
    private WorkoutExcercise workoutExcercise;
}
