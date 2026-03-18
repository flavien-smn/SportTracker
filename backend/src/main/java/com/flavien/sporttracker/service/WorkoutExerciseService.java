package com.flavien.sporttracker.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.workoutExercise.WorkoutExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.workoutExercise.WorkoutExerciseListResponse;
import com.flavien.sporttracker.entity.WorkoutExercise;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.WorkoutExerciseMapper;
import com.flavien.sporttracker.repository.WorkoutExerciseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

        private final WorkoutExerciseRepository workoutExerciseRepository;

        private final WorkoutExerciseMapper workoutExerciseMapper;

        private final WorkoutService workoutService;

        private final ExerciseService exerciseService;

        public WorkoutExerciseListResponse addWorkoutExerciseById(
                        UUID workoutId,
                        WorkoutExerciseCreateRequest workoutExerciseCreateRequest,
                        String email) {

                var workout = workoutService.getByIdAndEmail(workoutId, email);
                var exercise = exerciseService.getByIdAndAvailableForUser(workoutExerciseCreateRequest.exerciseId(),
                                email);

                long maxOrder = workoutExerciseRepository.findMaxOrderByWorkoutId(workoutId).orElse(0L);

                WorkoutExercise workoutExercise = WorkoutExercise.builder()
                                .workout(workout)
                                .exercise(exercise)
                                .orderWe((int) maxOrder + 1)
                                .build();

                return workoutExerciseMapper.toDto(workoutExerciseRepository.save(workoutExercise));

        }

        public void deleteWorkoutExerciseById(UUID workoutId, UUID workoutExerciseId, String email) {
                workoutService.checkWorkoutOwnership(workoutId, email);

                WorkoutExercise workoutExercise = workoutExerciseRepository
                                .findByIdAndWorkoutId(workoutExerciseId, workoutId)
                                .orElseThrow(() -> new NotFoundException("WorkoutExercise not found"));
                workoutExerciseRepository.delete(workoutExercise);

        }

        public WorkoutExercise getByIdAndWorkoutId(UUID id, UUID workoutId) {
                return workoutExerciseRepository.findByIdAndWorkoutId(id, workoutId)
                                .orElseThrow(() -> new NotFoundException("WorkoutExercise not found"));
        }

        public void checkWorkoutExerciseOwnership(UUID workoutExerciseId, UUID workoutId, String email) {
                workoutService.checkWorkoutOwnership(workoutId, email);
                if (!workoutExerciseRepository.existsByIdAndWorkoutId(workoutExerciseId, workoutId)) {
                        throw new NotFoundException("WorkoutExercise not found with id: " + workoutExerciseId);
                }
        }
}
