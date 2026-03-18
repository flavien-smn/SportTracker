package com.flavien.sporttracker.dto.request.exercise;

import jakarta.validation.constraints.NotBlank;

public record ExerciseCreateRequest(
                @NotBlank(message = "Exercise name is required") String name,
                @NotBlank(message = "Exercise description is required") String description) {

}
