package com.taskflow.sporttracker.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.taskflow.sporttracker.dto.request.workout.WorkoutUpdateRequest;
import com.taskflow.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutListResponse;
import com.taskflow.sporttracker.service.WorkoutService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkoutListResponse createWorkout(
            @Valid @RequestBody WorkoutCreateRequest workoutCreateRequest,
            @AuthenticationPrincipal Jwt jwt) {

        return workoutService.create(workoutCreateRequest, jwt.getSubject());
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<WorkoutListResponse> getAllByUserEmail(
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.getAllByUserEmail(jwt.getSubject());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public WorkoutDetailResponse getWorkoutDetailById(
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.getById(id, jwt.getSubject());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public WorkoutListResponse updateWorkoutById(
            @PathVariable(name = "id") UUID id,
            @Valid @RequestBody WorkoutUpdateRequest workoutUpdateRequest,
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.partialUpdateById(id, workoutUpdateRequest, jwt.getSubject());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteWorkoutById(
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        workoutService.deleteById(id, jwt.getSubject());
    }

}
