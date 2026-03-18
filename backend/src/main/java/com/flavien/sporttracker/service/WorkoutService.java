package com.flavien.sporttracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.workout.WorkoutCreateRequest;
import com.flavien.sporttracker.dto.request.workout.WorkoutUpdateRequest;
import com.flavien.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.flavien.sporttracker.dto.response.workout.WorkoutListResponse;
import com.flavien.sporttracker.entity.User;
import com.flavien.sporttracker.entity.Workout;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.WorkoutMapper;
import com.flavien.sporttracker.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    private final UserService userService;

    private final WorkoutMapper workoutMapper;

    public WorkoutListResponse create(WorkoutCreateRequest workoutCreateRequest, String email) {
        User user = userService.getByEmail(email);
        Workout workout = workoutMapper.toEntity(workoutCreateRequest);
        workout.setUser(user);
        var savedWorkout = workoutRepository.save(workout);

        return workoutMapper.toDto(savedWorkout);
    }

    public WorkoutDetailResponse getById(UUID id, String email) {
        var workout = workoutRepository.findByIdWithExercices(id)
                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + id));
        if (!workout.getUser().getEmail().equals(email)) {
            throw new NotFoundException("Workout not found with id: " + id);
        }
        return workoutMapper.toDetailDto(workout);
    }

    public List<WorkoutListResponse> getAllByUserEmail(String email) {
        return workoutMapper.toDtoList(workoutRepository.findAllByUserEmail(email));
    }

    public void deleteWorkoutById(UUID id, String email) {
        if (!workoutRepository.existsByIdAndUserEmail(id, email)) {
            throw new NotFoundException("Workout not found with id: " + id);
        }
        workoutRepository.deleteById(id);
    }

    public WorkoutListResponse partialUpdateWorkoutById(UUID id, WorkoutUpdateRequest workoutUpdateRequest,
            String email) {
        Workout workout = findByIdAndUserEmail(id, email);
        workoutUpdateRequest.name().ifPresent(workout::setName);
        workoutUpdateRequest.datePlanned().ifPresent(date -> workout.setDatePlanned(date));
        return workoutMapper.toDto(workoutRepository.save(workout));
    }

    public Workout findByIdAndUserEmail(UUID id, String email) {
        Workout workout = workoutRepository.findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + id));
        return workout;
    }

}
