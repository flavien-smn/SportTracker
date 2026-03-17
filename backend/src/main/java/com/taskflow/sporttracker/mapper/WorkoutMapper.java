package com.taskflow.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.request.seance.WorkoutCreateRequest;
import com.taskflow.sporttracker.dto.response.ExerciseSet.ExerciseSetDetailResponse;
import com.taskflow.sporttracker.dto.response.exercice.ExerciseDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutListResponse;
import com.taskflow.sporttracker.entity.ExerciseSet;
import com.taskflow.sporttracker.entity.Workout;
import com.taskflow.sporttracker.entity.WorkoutExercise;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout toEntity(WorkoutCreateRequest seanceCreateRequest);

    WorkoutListResponse toDto(Workout seance);

    @Mapping(target = "exercises", source = "workoutExercises")
    WorkoutDetailResponse toDetailDto(Workout seance);

    @Mapping(target = "id", source = "exercise.id")
    @Mapping(target = "name", source = "exercise.name")
    @Mapping(target = "description", source = "exercise.description")
    @Mapping(target = "order", source = "orderSe")
    ExerciseDetailResponse toExerciceDetail(WorkoutExercise seanceExercice);

    @Mapping(target = "order", source = "orderS")
    ExerciseSetDetailResponse toSerieDetail(ExerciseSet serie);

}
