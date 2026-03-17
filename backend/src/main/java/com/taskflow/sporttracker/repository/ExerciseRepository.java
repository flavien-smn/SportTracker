package com.taskflow.sporttracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.sporttracker.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

}
