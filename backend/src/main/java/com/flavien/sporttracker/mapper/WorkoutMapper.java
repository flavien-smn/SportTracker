package com.flavien.sporttracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flavien.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.flavien.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutListResponse;
import com.flavien.sporttracker.entity.Workout;

@Mapper(componentModel = "spring", uses = { ExerciseMapper.class })
public interface WorkoutMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "workoutExercises", ignore = true)
    Workout toEntity(WorkoutCreateRequest workoutCreateRequest);

    WorkoutListResponse toDto(Workout workout);

    @Mapping(target = "exercises", source = "workoutExercises")
    WorkoutDetailResponse toDetailDto(Workout workout);

    List<WorkoutListResponse> toDtoList(List<Workout> workouts);

}
