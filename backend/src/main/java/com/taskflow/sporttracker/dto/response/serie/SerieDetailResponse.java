package com.taskflow.sporttracker.dto.response.serie;

import java.util.UUID;

public record SerieDetailResponse(
        UUID id,
        Integer nbReps,
        Float weight,
        Integer ordre) {

}
