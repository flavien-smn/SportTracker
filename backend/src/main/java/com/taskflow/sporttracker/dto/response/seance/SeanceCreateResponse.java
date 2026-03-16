package com.taskflow.sporttracker.dto.response.seance;

import java.util.UUID;

public record SeanceCreateResponse(
        UUID id,
        String name,
        String datePlanned) {
}
