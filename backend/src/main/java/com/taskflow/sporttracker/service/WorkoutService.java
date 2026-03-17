package com.taskflow.sporttracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taskflow.sporttracker.dto.request.seance.WorkoutCreateRequest;
import com.taskflow.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.taskflow.sporttracker.dto.response.workout.WorkoutListResponse;
import com.taskflow.sporttracker.entity.User;
import com.taskflow.sporttracker.entity.Workout;
import com.taskflow.sporttracker.exception.customException.NotFoundException;
import com.taskflow.sporttracker.mapper.WorkoutMapper;
import com.taskflow.sporttracker.repository.WorkoutRepository;

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

    public List<WorkoutListResponse> getAllByUserEmail(String subject) {
        return workoutRepository.findAllByUserEmail(subject).stream()
                .map(workoutMapper::toDto)
                .toList();
    }

    public void deleteById(UUID id, String subject) {
        if (!workoutRepository.existsByIdAndUserEmail(id, subject)) {
            throw new NotFoundException("Workout not found with id: " + id);
        }
        workoutRepository.deleteById(id);
    }

}
