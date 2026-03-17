package com.taskflow.sporttracker.dto.response.exercise;

import java.util.List;
import java.util.UUID;

import com.taskflow.sporttracker.dto.response.ExerciseSet.ExerciseSetDetailResponse;

public record ExerciseDetailResponse(
                UUID id,
                String name,
                String description,
                Integer order,
                List<ExerciseSetDetailResponse> exerciseSets) {

}
