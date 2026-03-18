package com.flavien.sporttracker.dto.response.exercise;

import java.util.UUID;

public record ExerciseListResponse(
                UUID id,
                String name,
                String description,
                Boolean isGlobal) {

}
