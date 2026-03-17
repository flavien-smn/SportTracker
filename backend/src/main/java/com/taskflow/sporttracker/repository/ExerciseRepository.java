package com.taskflow.sporttracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskflow.sporttracker.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    @Query("SELECT e FROM Exercise e WHERE e.createdBy IS NULL OR e.createdBy.email = :email")
    List<Exercise> findAllAvailableForUser(@Param("email") String email);

}
