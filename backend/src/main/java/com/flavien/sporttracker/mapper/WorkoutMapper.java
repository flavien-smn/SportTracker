package com.flavien.sporttracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flavien.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.flavien.sporttracker.dto.response.exercise.ExerciseDetailResponse;
import com.flavien.sporttracker.dto.response.exerciseSet.ExerciseSetDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutListResponse;
import com.flavien.sporttracker.entity.ExerciseSet;
import com.flavien.sporttracker.entity.Workout;
import com.flavien.sporttracker.entity.WorkoutExercise;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout toEntity(WorkoutCreateRequest workoutCreateRequest);

    WorkoutListResponse toDto(Workout workout);

    @Mapping(target = "exercises", source = "workoutExercises")
    WorkoutDetailResponse toDetailDto(Workout workout);

    @Mapping(target = "id", source = "exercise.id")
    @Mapping(target = "name", source = "exercise.name")
    @Mapping(target = "description", source = "exercise.description")
    @Mapping(target = "order", source = "orderWe")
    ExerciseDetailResponse toExerciceDetail(WorkoutExercise workoutExercice);

    @Mapping(target = "order", source = "orderS")
    ExerciseSetDetailResponse toSerieDetail(ExerciseSet serie);

    List<WorkoutListResponse> toDtoList(List<Workout> workouts);

}
