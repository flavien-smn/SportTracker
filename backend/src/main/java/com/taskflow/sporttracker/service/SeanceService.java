package com.taskflow.sporttracker.service;

import org.springframework.stereotype.Service;

import com.taskflow.sporttracker.dto.request.seance.SeanceCreateRequest;
import com.taskflow.sporttracker.dto.response.seance.SeanceListResponse;
import com.taskflow.sporttracker.entity.Seance;
import com.taskflow.sporttracker.entity.User;
import com.taskflow.sporttracker.mapper.SeanceMapper;
import com.taskflow.sporttracker.repository.SeanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeanceService {

    private final SeanceRepository seanceRepository;

    private final UserService userService;

    private final SeanceMapper seanceMapper;

    public SeanceListResponse create(SeanceCreateRequest seanceCreateRequest, String email) {
        User user = userService.getByEmail(email);
        Seance seance = seanceMapper.toEntity(seanceCreateRequest);
        seance.setUser(user);
        var savedSeance = seanceRepository.save(seance);

        return seanceMapper.toDto(savedSeance);
    }

}
