package com.taskflow.sporttracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskflow.sporttracker.entity.Seance;

public interface SeanceRepository extends JpaRepository<Seance, UUID> {
    @Query("SELECT s FROM Seance s " +
            "LEFT JOIN FETCH s.seanceExercices se " +
            "LEFT JOIN FETCH se.exercice e " + // pour charger l'exercice
            "LEFT JOIN FETCH se.series sr " + // pour charger les series
            "WHERE s.id = :id")
    Optional<Seance> findByIdWithExercices(@Param("id") UUID id);

    @Query("SELECT s FROM Seance s" +
            " JOIN s.user u" +
            " WHERE u.email = :email")
    List<Seance> findAllByUserEmail(@Param("email") String email);
}
