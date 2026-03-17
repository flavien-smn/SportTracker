package com.taskflow.sporttracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.taskflow.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.taskflow.sporttracker.entity.Exercise;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    List<ExerciseListResponse> toDtoList(List<Exercise> exercises);

    @Mapping(target = "isGlobal", expression = "java(exercise.getCreatedBy() == null)")
    ExerciseListResponse toDto(Exercise exercise);

    Exercise toEntity(ExerciseCreateRequest exerciseCreateRequest);
}
