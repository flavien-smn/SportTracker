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
import com.flavien.sporttracker.repository.WorkoutExerciseRepository;
import com.flavien.sporttracker.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    private final WorkoutExerciseRepository workoutExerciseRepository;

    private final WorkoutRepository workoutRepository;

    private final ExerciseSetMapper exerciseSetMapper;

    public ExerciseSetDetailResponse create(ExerciseSetCreateRequest exerciseSetCreateRequest, UUID workoutId,
            UUID workoutExerciseId, String email) {

        workoutRepository.findByIdAndUserEmail(workoutId, email)
                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + workoutId));

        WorkoutExercise workoutExercise = workoutExerciseRepository
                .findByIdAndWorkoutId(workoutExerciseId, workoutId)
                .orElseThrow(() -> new NotFoundException("WorkoutExercise not found"));

        Integer maxOrder = exerciseSetRepository.findMaxByWorkoutExerciseId(workoutExerciseId).orElse(0);
        ExerciseSet exerciseSetEntity = exerciseSetMapper.toEntity(exerciseSetCreateRequest);
        exerciseSetEntity.setOrderS(maxOrder + 1);
        exerciseSetEntity.setWorkoutExercise(workoutExercise);

        return exerciseSetMapper.toExercisesSetDetail(exerciseSetRepository.save(exerciseSetEntity));
    }

    public void deleteById(UUID workoutId, UUID workoutExerciseId, UUID exerciseSetId, String subject) {
        workoutRepository.findByIdAndUserEmail(workoutId, subject)
                .orElseThrow(() -> new NotFoundException("Workout not found with id: " + workoutId));
        workoutExerciseRepository.findByIdAndWorkoutId(workoutExerciseId, workoutId)
                .orElseThrow(() -> new NotFoundException("WorkoutExercise not found with id: " + workoutExerciseId));
        ExerciseSet exerciseSet = exerciseSetRepository.findByIdAndWorkoutExerciseId(exerciseSetId, workoutExerciseId)
                .orElseThrow(() -> new NotFoundException("ExerciseSet not found with id: " + exerciseSetId));
        exerciseSetRepository.delete(exerciseSet);
    }

}
