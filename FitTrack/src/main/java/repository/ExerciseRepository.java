package repository;

import entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // Find exercises by muscle group
    List<Exercise> findByMuscleGroup(Exercise.MuscleGroup muscleGroup);

    // Find exercise by name
    Optional<Exercise> findByName(String name);

    // Find exercises by difficulty level
    List<Exercise> findByDifficultyLevel(String difficultyLevel);

    // Custom query to find exercises by muscle group and difficulty level
    List<Exercise> findByMuscleGroupAndDifficultyLevel(
            Exercise.MuscleGroup muscleGroup,
            String difficultyLevel
    );
}
