package com.flavien.sporttracker.dto.request.workoutExercise;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record WorkoutExerciseCreateRequest(
                @NotNull(message = "Exercise ID is required") UUID exerciseId) {
}
