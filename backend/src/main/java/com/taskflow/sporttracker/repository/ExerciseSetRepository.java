package com.taskflow.sporttracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.sporttracker.entity.ExerciseSet;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {

}
