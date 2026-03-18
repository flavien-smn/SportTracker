package com.flavien.sporttracker.dto.response.exerciseSet;

import java.util.UUID;

public record ExerciseSetDetailResponse(
                UUID id,
                Integer repetitions,
                Float weight,
                Integer order) {

}
