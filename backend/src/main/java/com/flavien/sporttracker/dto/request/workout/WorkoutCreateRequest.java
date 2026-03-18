package com.flavien.sporttracker.dto.request.workout;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record WorkoutCreateRequest(
                @NotBlank(message = "Name is required") String name,
                LocalDate datePlanned) {
}
