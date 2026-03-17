package com.taskflow.sporttracker.dto.response.ExerciseSet;

import java.util.UUID;

public record ExerciseSetDetailResponse(
        UUID id,
        Integer nbReps,
        Float weight,
        Integer order) {

}
