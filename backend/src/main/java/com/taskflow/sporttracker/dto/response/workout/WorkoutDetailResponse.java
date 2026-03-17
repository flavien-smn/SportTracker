package com.taskflow.sporttracker.dto.response.workout;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.taskflow.sporttracker.dto.response.exercice.ExerciseDetailResponse;

public record WorkoutDetailResponse(
        UUID id,
        String name,
        LocalDate datePlanned,
        List<ExerciseDetailResponse> exercises) {

}
