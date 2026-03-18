package com.flavien.sporttracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flavien.sporttracker.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    @Query("SELECT e FROM Exercise e WHERE e.createdBy IS NULL OR e.createdBy.email = :email")
    List<Exercise> findAllAvailableForUser(@Param("email") String email);

    @Query("SELECT e FROM Exercise e WHERE e.id = :id AND e.createdBy IS NOT NULL AND e.createdBy.email = :email")
    Optional<Exercise> findByIdAndCreatedBy(@Param("id") UUID id, @Param("email") String email);

    @Query("SELECT e FROM Exercise e WHERE e.id = :id AND (e.createdBy IS NULL OR e.createdBy.email = :email)")
    Optional<Exercise> findByIdAndAvailableForUser(@Param("id") UUID id, @Param("email") String email);

}
