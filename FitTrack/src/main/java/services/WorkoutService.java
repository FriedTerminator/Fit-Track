package services;

import entities.Exercise;
import entities.Workout;
import entities.WorkoutExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WorkoutRepository;
import repository.ExerciseRepository;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Transactional
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout addExerciseToWorkout(Long workoutId, Long exerciseId, int sets, int reps) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        workout.addExercise(exercise, sets, reps);
        return workoutRepository.save(workout);
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
    }

    public List<Workout> getWorkoutsByMuscleGroup(Exercise.MuscleGroup muscleGroup) {
        return workoutRepository.findByMuscleGroup(muscleGroup);
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
}