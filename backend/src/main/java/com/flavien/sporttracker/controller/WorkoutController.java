package com.flavien.sporttracker.controller;

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

import com.flavien.sporttracker.dto.request.exerciseSet.ExerciseSetCreateRequest;
import com.flavien.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.flavien.sporttracker.dto.request.workout.WorkoutUpdateRequest;
import com.flavien.sporttracker.dto.request.workoutExercise.WorkoutExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.exerciseSet.ExerciseSetDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutListResponse;
import com.flavien.sporttracker.dto.response.workoutExercise.WorkoutExerciseListResponse;
import com.flavien.sporttracker.service.ExerciseSetService;
import com.flavien.sporttracker.service.WorkoutExerciseService;
import com.flavien.sporttracker.service.WorkoutService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    private final WorkoutExerciseService workoutExerciseService;

    private final ExerciseSetService exerciseSetService;

    /**
     * Create a new workout for the authenticated user.
     * 
     * @param workoutCreateRequest The details of the workout to create.
     * @param jwt                  The JWT token containing the authenticated user's
     *                             information.
     * @return The created workout's details.
     */
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkoutListResponse createWorkout(
            @Valid @RequestBody WorkoutCreateRequest workoutCreateRequest,
            @AuthenticationPrincipal Jwt jwt) {

        return workoutService.create(workoutCreateRequest, jwt.getSubject());
    }

    /**
     * Get all workouts for the authenticated user.
     * 
     * @param jwt The JWT token containing the authenticated user's information.
     * @return A list of workouts belonging to the authenticated user.
     */
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<WorkoutListResponse> getAllByUserEmail(
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.getAllByUserEmail(jwt.getSubject());
    }

    /**
     * Get the details of a specific workout by its ID for the authenticated user.
     * 
     * @param id  The ID of the workout to retrieve.
     * @param jwt The JWT token containing the authenticated user's information.
     * @return The details of the requested workout if it belongs to the
     *         authenticated user.
     */
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public WorkoutDetailResponse getWorkoutDetailById(
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.getById(id, jwt.getSubject());
    }

    /**
     * Partially update a workout's details by its ID for the authenticated user.
     * 
     * @param id                   The ID of the workout to update.
     * @param workoutUpdateRequest The details to update for the workout.
     * @param jwt                  The JWT token containing the authenticated user's
     *                             information.
     * @return The updated workout's details if the workout belongs to the
     *         authenticated user.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public WorkoutListResponse updateWorkoutById(
            @PathVariable(name = "id") UUID id,
            @Valid @RequestBody WorkoutUpdateRequest workoutUpdateRequest,
            @AuthenticationPrincipal Jwt jwt) {
        return workoutService.partialUpdateWorkoutById(id, workoutUpdateRequest, jwt.getSubject());
    }

    /**
     * Delete a workout by its ID for the authenticated user.
     * 
     * @param id  The ID of the workout to delete.
     * @param jwt The JWT token containing the authenticated user's information.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteWorkoutById(
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        workoutService.deleteWorkoutById(id, jwt.getSubject());
    }

    /**
     * Add an exercise to a workout by the workout's ID for the authenticated user.
     * 
     * @param workoutExerciseCreateRequest The details of the exercise to add to the
     *                                     workout.
     * @param id                           The ID of the workout to which the
     *                                     exercise will be added.
     * @param jwt                          The JWT token containing the
     *                                     authenticated
     *                                     user's information.
     * @return The details of the added exercise within the workout if the workout
     *         belongs to the authenticated user.
     */
    @PostMapping("/{id}/exercises")
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkoutExerciseListResponse addExerciseToWorkoutById(
            @Valid @RequestBody WorkoutExerciseCreateRequest workoutExerciseCreateRequest,
            @PathVariable(name = "id") UUID id,
            @AuthenticationPrincipal Jwt jwt) {

        return workoutExerciseService.addWorkoutExerciseById(id, workoutExerciseCreateRequest, jwt.getSubject());
    }

    /**
     * Delete an exercise from a workout by the workout's ID and the exercise's ID
     * for the authenticated user.
     * 
     * @param workoutId         The ID of the workout from which the exercise will
     *                          be
     *                          deleted.
     * @param workoutExerciseId The ID of the exercise to delete from the workout.
     * @param jwt               The JWT token containing the authenticated user's
     *                          information.
     */
    @DeleteMapping("/{workoutId}/exercises/{workoutExerciseId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteWorkoutExerciseById(
            @PathVariable(name = "workoutId") UUID workoutId,
            @PathVariable(name = "workoutExerciseId") UUID workoutExerciseId,
            @AuthenticationPrincipal Jwt jwt) {
        workoutExerciseService.deleteWorkoutExerciseById(workoutId, workoutExerciseId, jwt.getSubject());
    }

    /**
     * Add a set to an exercise within a workout by the workout's ID, the
     * exercise's ID, and the set's details for the authenticated user.
     * 
     * @param exerciseSetCreateRequest The details of the set to add to the
     *                                 exercise within the workout.
     * @param workoutId                The ID of the workout to which the exercise
     *                                 belongs.
     * @param workoutExerciseId        The ID of the exercise to which the set will
     *                                 be added.
     * @param jwt                      The JWT token containing the authenticated
     *                                 user's information.
     * @return The details of the added set within the exercise if the workout
     *         belongs to the authenticated user.
     */
    @PostMapping("/{workoutId}/exercises/{workoutExerciseId}/sets")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ExerciseSetDetailResponse addSet(
            @Valid @RequestBody ExerciseSetCreateRequest exerciseSetCreateRequest,
            @PathVariable(name = "workoutId") UUID workoutId,
            @PathVariable(name = "workoutExerciseId") UUID workoutExerciseId,
            @AuthenticationPrincipal Jwt jwt) {
        return exerciseSetService.createExerciseSet(exerciseSetCreateRequest, workoutId, workoutExerciseId,
                jwt.getSubject());
    }

    /**
     * Delete a set from an exercise within a workout by the workout's ID, the
     * exercise's ID, and the set's ID for the authenticated user.
     * 
     * @param workoutId         The ID of the workout from which the set will be
     *                          deleted.
     * @param workoutExerciseId The ID of the exercise from which the set will be
     *                          deleted.
     * @param exerciseSetId     The ID of the set to delete from the exercise.
     * @param jwt               The JWT token containing the authenticated user's
     *                          information.
     */
    @DeleteMapping("/{workoutId}/exercises/{workoutExerciseId}/sets/{exerciseSetId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSet(
            @PathVariable(name = "workoutId") UUID workoutId,
            @PathVariable(name = "workoutExerciseId") UUID workoutExerciseId,
            @PathVariable(name = "exerciseSetId") UUID exerciseSetId,
            @AuthenticationPrincipal Jwt jwt) {
        exerciseSetService.deleteExerciseSetById(workoutId, workoutExerciseId, exerciseSetId, jwt.getSubject());
    }

}
