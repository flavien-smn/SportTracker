package com.flavien.sporttracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flavien.sporttracker.entity.ExerciseSet;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {

    @Query("SELECT MAX(es.orderS) FROM ExerciseSet es WHERE es.workoutExercise.id = :workoutExerciseId")
    Optional<Integer> findMaxByWorkoutExerciseId(UUID workoutExerciseId);

    Optional<ExerciseSet> findByIdAndWorkoutExerciseId(UUID id, UUID workoutExerciseId);

}
