package com.taskflow.sporttracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.sporttracker.entity.Serie;

public interface SerieRepository extends JpaRepository<Serie, UUID> {

}
