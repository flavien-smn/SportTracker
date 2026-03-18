package com.flavien.sporttracker.dto.response.exercise;

import java.util.List;
import java.util.UUID;

import com.flavien.sporttracker.dto.response.exerciseSet.ExerciseSetDetailResponse;

public record ExerciseDetailResponse(
                UUID id,
                String name,
                String description,
                Integer order,
                List<ExerciseSetDetailResponse> exerciseSets) {

}
