package entities;

import jakarta.persistence.*;

@Entity
public class WorkoutExcercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Exercise exercise;
}
