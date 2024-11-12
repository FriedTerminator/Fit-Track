package controller;

import entities.Exercise;
import entities.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        Workout createdWorkout = workoutService.createWorkout(workout);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<Workout> addExerciseToWorkout(
            @PathVariable Long workoutId,
            @RequestParam Long exerciseId,
            @RequestParam int sets,
            @RequestParam int reps
    ) {
        Workout updatedWorkout = workoutService.addExerciseToWorkout(workoutId, exerciseId, sets, reps);
        return new ResponseEntity<>(updatedWorkout, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Workout workout = workoutService.getWorkoutById(id);
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }

    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<Workout>> getWorkoutsByMuscleGroup(
            @PathVariable Exercise.MuscleGroup muscleGroup
    ) {
        List<Workout> workouts = workoutService.getWorkoutsByMuscleGroup(muscleGroup);
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }
}