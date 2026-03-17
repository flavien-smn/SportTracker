package com.taskflow.sporttracker.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.sporttracker.dto.request.seance.SeanceCreateRequest;
import com.taskflow.sporttracker.dto.response.seance.SeanceDetailResponse;
import com.taskflow.sporttracker.dto.response.seance.SeanceListResponse;
import com.taskflow.sporttracker.service.SeanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seances")
@RequiredArgsConstructor
public class SeanceController {

    private final SeanceService seanceService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public SeanceListResponse createSeance(
            @Valid @RequestBody SeanceCreateRequest seanceCreateRequest,
            @AuthenticationPrincipal Jwt jwt) {

        return seanceService.create(seanceCreateRequest, jwt.getSubject());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public SeanceDetailResponse getSeanceDetailById(
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        return seanceService.getById(id, jwt.getSubject());
    }

}
