package com.taskflow.sporttracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.sporttracker.entity.SeanceExercice;

public interface SeanceExerciceRepository extends JpaRepository<SeanceExercice, UUID> {

}
