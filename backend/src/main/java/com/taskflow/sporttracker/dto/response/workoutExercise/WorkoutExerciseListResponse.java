package com.taskflow.sporttracker.dto.response.workoutExercise;

import java.util.UUID;

public record WorkoutExerciseListResponse(
        UUID id,
        UUID exerciseId,
        UUID workoutId,
        Integer order) {

}
