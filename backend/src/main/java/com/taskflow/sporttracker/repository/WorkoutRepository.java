package com.taskflow.sporttracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskflow.sporttracker.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
        @Query("SELECT s FROM Workout s " +
                        "LEFT JOIN FETCH s.workoutExercises we " +
                        "LEFT JOIN FETCH we.exercise e " +
                        "LEFT JOIN FETCH we.exerciseSets ws " +
                        "WHERE s.id = :id")
        Optional<Workout> findByIdWithExercices(@Param("id") UUID id);

        @Query("SELECT s FROM Workout s" +
                        " JOIN s.user u" +
                        " WHERE u.email = :email")
        List<Workout> findAllByUserEmail(@Param("email") String email);

        @Query("SELECT COUNT(s) > 0 FROM Workout s" +
                        " JOIN s.user u" +
                        " WHERE s.id = :id AND u.email = :email")
        boolean existsByIdAndUserEmail(@Param("id") UUID id, @Param("email") String email);
}
