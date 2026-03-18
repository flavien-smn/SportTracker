package com.flavien.sporttracker.dto.request.exerciseSet;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExerciseSetCreateRequest(
        @NotNull(message = "Repetitions cannot be null") @Max(value = 100, message = "Repetitions cannot exceed 100") @Min(value = 1, message = "Repetitions cannot be less than 1") Integer repetitions,
        @NotNull(message = "Weight cannot be null") Double weight) {
}
