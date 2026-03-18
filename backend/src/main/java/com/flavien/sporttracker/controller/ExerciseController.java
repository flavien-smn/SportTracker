package com.flavien.sporttracker.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flavien.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.flavien.sporttracker.service.ExerciseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    /**
     * Get all exercises available for the authenticated user.
     * 
     * @param jwt The JWT token containing the authenticated user's information.
     * @return A list of exercises available for the authenticated user.
     */
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<ExerciseListResponse> getAllAvailableByUser(@AuthenticationPrincipal Jwt jwt) {
        return exerciseService.getAllAvailableForUser(jwt.getSubject());
    }

    /**
     * Create a new exercise for the authenticated user.
     * 
     * @param exerciseCreateRequest The details of the exercise to create.
     * @param jwt                   The JWT token containing the authenticated
     *                              user's
     *                              information.
     * @return The created exercise's details.
     */
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ExerciseListResponse create(
            @Valid @RequestBody ExerciseCreateRequest exerciseCreateRequest,
            @AuthenticationPrincipal Jwt jwt) {

        return exerciseService.create(exerciseCreateRequest, jwt.getSubject());
    }

    /**
     * Delete an exercise by its ID for the authenticated user.
     * 
     * @param id  The ID of the exercise to delete.
     * @param jwt The JWT token containing the authenticated user's information.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") UUID id, @AuthenticationPrincipal Jwt jwt) {
        exerciseService.deleteById(id, jwt.getSubject());
    }
}
