package com.flavien.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flavien.sporttracker.dto.request.exerciseSet.ExerciseSetCreateRequest;
import com.flavien.sporttracker.dto.response.exerciseSet.ExerciseSetDetailResponse;
import com.flavien.sporttracker.entity.ExerciseSet;

@Mapper(componentModel = "spring")
public interface ExerciseSetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workoutExercise", ignore = true)
    @Mapping(target = "orderS", ignore = true)
    ExerciseSet toEntity(ExerciseSetCreateRequest exerciseSet);

    @Mapping(target = "order", source = "orderS")
    ExerciseSetDetailResponse toExercisesSetDetail(ExerciseSet serie);

}
