package services;

import entities.Exercise;
import errorHandling.ExerciseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ExerciseRepository;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // Create a new exercise
    @Transactional
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // Get exercise by ID
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with ID: " + id));
    }

    // Get all exercises
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // Get exercises by muscle group
    public List<Exercise> getExercisesByMuscleGroup(Exercise.MuscleGroup muscleGroup) {
        return exerciseRepository.findByMuscleGroup(muscleGroup);
    }

    // Update an existing exercise
    @Transactional
    public Exercise updateExercise(Long id, Exercise exerciseDetails) {
        Exercise existingExercise = getExerciseById(id);

        existingExercise.setName(exerciseDetails.getName());
        existingExercise.setMuscleGroup(exerciseDetails.getMuscleGroup());
        existingExercise.setDescription(exerciseDetails.getDescription());

        return exerciseRepository.save(existingExercise);
    }

    // Delete an exercise
    @Transactional
    public void deleteExercise(Long id) {
        Exercise exercise = getExerciseById(id);
        exerciseRepository.delete(exercise);
    }
}
