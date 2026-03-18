package com.taskflow.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.response.workoutExercise.WorkoutExerciseListResponse;
import com.taskflow.sporttracker.entity.WorkoutExercise;

@Mapper(componentModel = "spring")
public interface WorkoutExerciseMapper {

    @Mapping(target = "exerciseId", source = "exercise.id")
    @Mapping(target = "workoutId", source = "workout.id")
    @Mapping(target = "order", source = "orderWe")
    WorkoutExerciseListResponse toDto(WorkoutExercise workoutExercise);

}
