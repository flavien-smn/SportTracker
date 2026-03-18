package com.flavien.sporttracker.dto.response.workout;

import java.util.UUID;

public record WorkoutCreateResponse(
                UUID id,
                String name,
                String datePlanned) {
}
