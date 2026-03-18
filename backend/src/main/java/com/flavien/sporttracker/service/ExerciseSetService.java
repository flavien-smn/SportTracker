package com.flavien.sporttracker.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.exerciseSet.ExerciseSetCreateRequest;
import com.flavien.sporttracker.dto.response.exerciseSet.ExerciseSetDetailResponse;
import com.flavien.sporttracker.entity.ExerciseSet;
import com.flavien.sporttracker.entity.WorkoutExercise;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.ExerciseSetMapper;
import com.flavien.sporttracker.repository.ExerciseSetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseSetService {

        private final ExerciseSetRepository exerciseSetRepository;

        private final ExerciseSetMapper exerciseSetMapper;

        private final WorkoutService workoutService;

        private final WorkoutExerciseService workoutExerciseService;

        public ExerciseSetDetailResponse createExerciseSet(ExerciseSetCreateRequest exerciseSetCreateRequest,
                        UUID workoutId,
                        UUID workoutExerciseId, String email) {

                workoutService.checkWorkoutOwnership(workoutId, email);

                WorkoutExercise workoutExercise = workoutExerciseService.getByIdAndWorkoutId(workoutExerciseId,
                                workoutId);

                Integer maxOrder = exerciseSetRepository.findMaxByWorkoutExerciseId(workoutExerciseId).orElse(0);
                ExerciseSet exerciseSetEntity = exerciseSetMapper.toEntity(exerciseSetCreateRequest);
                exerciseSetEntity.setOrderS(maxOrder + 1);
                exerciseSetEntity.setWorkoutExercise(workoutExercise);

                return exerciseSetMapper.toExercisesSetDetail(exerciseSetRepository.save(exerciseSetEntity));
        }

        public void deleteExerciseSetById(UUID workoutId, UUID workoutExerciseId, UUID exerciseSetId, String email) {
                workoutExerciseService.checkWorkoutExerciseOwnership(workoutExerciseId, workoutId, email);
                ExerciseSet exerciseSet = exerciseSetRepository
                                .findByIdAndWorkoutExerciseId(exerciseSetId, workoutExerciseId)
                                .orElseThrow(() -> new NotFoundException(
                                                "ExerciseSet not found with id: " + exerciseSetId));
                exerciseSetRepository.delete(exerciseSet);
        }

}
