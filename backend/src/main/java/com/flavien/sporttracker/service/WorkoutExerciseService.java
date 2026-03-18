package com.flavien.sporttracker.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.workoutExercise.WorkoutExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.workoutExercise.WorkoutExerciseListResponse;
import com.flavien.sporttracker.entity.WorkoutExercise;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.WorkoutExerciseMapper;
import com.flavien.sporttracker.repository.ExerciseRepository;
import com.flavien.sporttracker.repository.WorkoutExerciseRepository;
import com.flavien.sporttracker.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

        private final WorkoutExerciseRepository workoutExerciseRepository;

        private final WorkoutRepository workoutRepository;

        private final ExerciseRepository exerciseRepository;

        private final WorkoutExerciseMapper workoutExerciseMapper;

        public WorkoutExerciseListResponse addWorkoutExerciseById(
                        UUID workoutId,
                        WorkoutExerciseCreateRequest workoutExerciseCreateRequest,
                        String email) {

                var workout = workoutRepository.findByIdAndUserEmail(workoutId, email)
                                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + workoutId));
                var exercise = exerciseRepository
                                .findByIdAndAvailableForUser(workoutExerciseCreateRequest.exerciseId(), email)
                                .orElseThrow(() -> new NotFoundException(
                                                "Exercise not found with id: "
                                                                + workoutExerciseCreateRequest.exerciseId()));

                long maxOrder = workoutExerciseRepository.findMaxOrderByWorkoutId(workoutId).orElse(0L);

                WorkoutExercise workoutExercise = WorkoutExercise.builder()
                                .workout(workout)
                                .exercise(exercise)
                                .orderWe((int) maxOrder + 1)
                                .build();

                return workoutExerciseMapper.toDto(workoutExerciseRepository.save(workoutExercise));

        }

        public void deleteWorkoutExerciseById(UUID workoutId, UUID workoutExerciseId, String email) {
                workoutRepository.findByIdAndUserEmail(workoutId, email)
                                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + workoutId));

                WorkoutExercise workoutExercise = workoutExerciseRepository
                                .findByIdAndWorkoutId(workoutExerciseId, workoutId)
                                .orElseThrow(() -> new NotFoundException("WorkoutExercise not found"));
                workoutExerciseRepository.delete(workoutExercise);

        }
}
