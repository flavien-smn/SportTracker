package com.flavien.sporttracker.dto.response.workout;

import java.time.LocalDate;
import java.util.UUID;

public record WorkoutListResponse(
                UUID id,
                String name,
                LocalDate datePlanned) {
}
