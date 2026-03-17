package com.taskflow.sporttracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.taskflow.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.taskflow.sporttracker.service.ExerciseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<ExerciseListResponse> getAllAvailableByUser(@AuthenticationPrincipal Jwt jwt) {
        return exerciseService.getAllAvailableForUser(jwt.getSubject());
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ExerciseListResponse create(
            @Valid @RequestBody ExerciseCreateRequest exerciseCreateRequest,
            @AuthenticationPrincipal Jwt jwt) {

        return exerciseService.create(exerciseCreateRequest, jwt.getSubject());
    }

}
