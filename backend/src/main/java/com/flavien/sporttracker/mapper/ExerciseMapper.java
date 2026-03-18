package com.flavien.sporttracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flavien.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.exercise.ExerciseDetailResponse;
import com.flavien.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.flavien.sporttracker.entity.Exercise;
import com.flavien.sporttracker.entity.WorkoutExercise;

@Mapper(componentModel = "spring", uses = { ExerciseSetMapper.class })
public interface ExerciseMapper {

    List<ExerciseListResponse> toDtoList(List<Exercise> exercises);

    @Mapping(target = "isGlobal", expression = "java(exercise.getCreatedBy() == null)")
    ExerciseListResponse toDto(Exercise exercise);

    @Mapping(target = "id", source = "exercise.id")
    @Mapping(target = "name", source = "exercise.name")
    @Mapping(target = "description", source = "exercise.description")
    @Mapping(target = "order", source = "orderWe")
    ExerciseDetailResponse toExerciceDetail(WorkoutExercise workoutExercice);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Exercise toEntity(ExerciseCreateRequest exerciseCreateRequest);
}
