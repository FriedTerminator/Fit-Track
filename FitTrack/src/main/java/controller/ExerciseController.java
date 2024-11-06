package controller;

import entities.Exercise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // Create a new exercise
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody Exercise exercise) {
        Exercise newExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }

    // Get all exercises
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    // Get exercise by ID
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    // Get exercises by muscle group
    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<Exercise>> getExercisesByMuscleGroup(
            @PathVariable Exercise.MuscleGroup muscleGroup
    ) {
        List<Exercise> exercises = exerciseService.getExercisesByMuscleGroup(muscleGroup);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    // Update an exercise
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(
            @PathVariable Long id,
            @Valid @RequestBody Exercise exerciseDetails
    ) {
        Exercise updatedExercise = exerciseService.updateExercise(id, exerciseDetails);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    // Delete an exercise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
