package com.taskflow.sporttracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskflow.sporttracker.entity.WorkoutExercise;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, UUID> {

    @Query("SELECT max(we.orderWe) FROM WorkoutExercise we WHERE we.workout.id = :workoutId")
    Optional<Long> findMaxOrderByWorkoutId(UUID workoutId);

    Optional<WorkoutExercise> findByIdAndWorkoutId(UUID workoutExerciseId, UUID workoutId);

}
