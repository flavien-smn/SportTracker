package com.taskflow.sporttracker.dto.response.exercise;

import java.util.UUID;

public record ExerciseListResponse(
        UUID id,
        String name,
        String description,
        Boolean isGlobal) {

}
