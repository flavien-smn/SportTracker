package com.taskflow.sporttracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.taskflow.sporttracker.dto.response.ExerciseSet.ExerciseSetDetailResponse;
import com.taskflow.sporttracker.dto.response.exercise.ExerciseDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutListResponse;
import com.taskflow.sporttracker.entity.ExerciseSet;
import com.taskflow.sporttracker.entity.Workout;
import com.taskflow.sporttracker.entity.WorkoutExercise;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout toEntity(WorkoutCreateRequest workoutCreateRequest);

    WorkoutListResponse toDto(Workout workout);

    @Mapping(target = "exercises", source = "workoutExercises")
    WorkoutDetailResponse toDetailDto(Workout workout);

    @Mapping(target = "id", source = "exercise.id")
    @Mapping(target = "name", source = "exercise.name")
    @Mapping(target = "description", source = "exercise.description")
    @Mapping(target = "order", source = "orderSe")
    ExerciseDetailResponse toExerciceDetail(WorkoutExercise workoutExercice);

    @Mapping(target = "order", source = "orderS")
    ExerciseSetDetailResponse toSerieDetail(ExerciseSet serie);

    List<WorkoutListResponse> toDtoList(List<Workout> workouts);

}
