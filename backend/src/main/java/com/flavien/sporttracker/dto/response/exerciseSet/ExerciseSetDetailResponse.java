package com.flavien.sporttracker.dto.response.exerciseSet;

import java.util.UUID;

public record ExerciseSetDetailResponse(
        UUID id,
        Integer nbReps,
        Float weight,
        Integer order) {

}
