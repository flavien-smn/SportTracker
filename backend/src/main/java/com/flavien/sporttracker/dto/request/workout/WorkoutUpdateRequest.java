package com.flavien.sporttracker.dto.request.workout;

import java.time.LocalDate;
import java.util.Optional;

public record WorkoutUpdateRequest(
                Optional<String> name,
                Optional<LocalDate> datePlanned) {
}
