package com.flavien.sporttracker.dto.request.workout;

import java.time.LocalDate;
import java.util.Optional;

import org.openapitools.jackson.nullable.JsonNullable;

public record WorkoutUpdateRequest(
        Optional<String> name,
        JsonNullable<LocalDate> datePlanned) {
}
