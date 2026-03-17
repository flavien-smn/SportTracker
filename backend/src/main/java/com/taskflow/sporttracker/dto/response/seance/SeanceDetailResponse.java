package com.taskflow.sporttracker.dto.response.seance;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.taskflow.sporttracker.dto.response.exercice.ExerciceDetailResponse;

public record SeanceDetailResponse(
        UUID id,
        String name,
        LocalDate datePlanned,
        List<ExerciceDetailResponse> exercices) {

}
