package com.taskflow.sporttracker.dto.response.exercice;

import java.util.List;
import java.util.UUID;

import com.taskflow.sporttracker.dto.response.serie.SerieDetailResponse;

public record ExerciceDetailResponse(
        UUID id,
        String name,
        String description,
        List<SerieDetailResponse> series) {

}
