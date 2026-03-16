package com.taskflow.sporttracker.dto.response.seance;

import java.time.LocalDate;
import java.util.UUID;

public record SeanceListResponse(
        UUID id,
        String name,
        LocalDate datePlanned) {
}
