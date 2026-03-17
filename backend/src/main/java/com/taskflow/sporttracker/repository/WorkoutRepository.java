package com.taskflow.sporttracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskflow.sporttracker.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
        @Query("SELECT w FROM Workout w " +
                        "LEFT JOIN FETCH w.workoutExercises we " +
                        "LEFT JOIN FETCH we.exercise e " +
                        "LEFT JOIN FETCH we.exerciseSets es " +
                        "WHERE w.id = :id")
        Optional<Workout> findByIdWithExercices(@Param("id") UUID id);

        @Query("SELECT w FROM Workout w" +
                        " JOIN w.user u" +
                        " WHERE u.email = :email")
        List<Workout> findAllByUserEmail(@Param("email") String email);

        @Query("SELECT COUNT(w) > 0 FROM Workout w" +
                        " JOIN w.user u" +
                        " WHERE w.id = :id AND u.email = :email")
        boolean existsByIdAndUserEmail(@Param("id") UUID id, @Param("email") String email);

        @Query("SELECT w FROM Workout w" +
                        " JOIN w.user u" +
                        " WHERE w.id = :id AND u.email = :email")
        Optional<Workout> findByIdAndUserEmail(@Param("id") UUID id, @Param("email") String email);
}
