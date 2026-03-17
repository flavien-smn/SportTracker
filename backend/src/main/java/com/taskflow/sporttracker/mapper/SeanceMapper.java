package com.taskflow.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.request.seance.SeanceCreateRequest;
import com.taskflow.sporttracker.dto.response.exercice.ExerciceDetailResponse;
import com.taskflow.sporttracker.dto.response.seance.SeanceDetailResponse;
import com.taskflow.sporttracker.dto.response.seance.SeanceListResponse;
import com.taskflow.sporttracker.dto.response.serie.SerieDetailResponse;
import com.taskflow.sporttracker.entity.Seance;
import com.taskflow.sporttracker.entity.SeanceExercice;
import com.taskflow.sporttracker.entity.Serie;

@Mapper(componentModel = "spring")
public interface SeanceMapper {

    Seance toEntity(SeanceCreateRequest seanceCreateRequest);

    SeanceListResponse toDto(Seance seance);

    @Mapping(target = "exercices", source = "seanceExercices")
    SeanceDetailResponse toDetailDto(Seance seance);

    @Mapping(target = "id", source = "exercice.id")
    @Mapping(target = "name", source = "exercice.name")
    @Mapping(target = "description", source = "exercice.description")
    @Mapping(target = "order", source = "orderSe")
    ExerciceDetailResponse toExerciceDetail(SeanceExercice seanceExercice);

    @Mapping(target = "order", source = "orderS")
    SerieDetailResponse toSerieDetail(Serie serie);

}
