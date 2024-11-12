package repository;

import entities.Exercise;
import entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByMuscleGroup(Exercise.MuscleGroup muscleGroup);
}
