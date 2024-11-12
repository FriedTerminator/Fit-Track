package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Workout name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Muscle group for the workout
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group")
    private Exercise.MuscleGroup muscleGroup;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> exercises = new ArrayList<>();

    // Difficulty level for the workout
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    // Constructors
    public Workout() {}

    public Workout(String name, Exercise.MuscleGroup muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    // Method to add exercise to workout
    public void addExercise(Exercise exercise, int sets, int reps) {
        WorkoutExercise workoutExercise = new WorkoutExercise(this, exercise, sets, reps);
        exercises.add(workoutExercise);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise.MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(Exercise.MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}