package com.taskflow.sporttracker.mapper;

import org.mapstruct.Mapper;

import com.taskflow.sporttracker.dto.request.seance.SeanceCreateRequest;
import com.taskflow.sporttracker.dto.response.seance.SeanceListResponse;
import com.taskflow.sporttracker.entity.Seance;

@Mapper(componentModel = "spring")
public interface SeanceMapper {

    Seance toEntity(SeanceCreateRequest seanceCreateRequest);

    SeanceListResponse toDto(Seance seance);

}
