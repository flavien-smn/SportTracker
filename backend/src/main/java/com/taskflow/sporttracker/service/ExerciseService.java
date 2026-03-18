package com.taskflow.sporttracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taskflow.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.taskflow.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.taskflow.sporttracker.entity.Exercise;
import com.taskflow.sporttracker.entity.User;
import com.taskflow.sporttracker.exception.customException.NotFoundException;
import com.taskflow.sporttracker.mapper.ExerciseMapper;
import com.taskflow.sporttracker.repository.ExerciseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    private final UserService userService;

    public List<ExerciseListResponse> getAllAvailableForUser(String email) {
        return exerciseMapper.toDtoList(exerciseRepository.findAllAvailableForUser(email));
    }

    public ExerciseListResponse create(ExerciseCreateRequest exerciseCreateRequest, String subject) {
        User user = userService.getByEmail(subject);
        Exercise exercise = exerciseMapper.toEntity(exerciseCreateRequest);
        exercise.setCreatedBy(user);
        return exerciseMapper.toDto(exerciseRepository.save(exercise));
    }

    public void deleteById(UUID id, String email) {
        Optional<Exercise> exerciseRes = exerciseRepository.findByIdAndCreatedBy(id, email);
        if (exerciseRes.isEmpty()) {
            throw new NotFoundException("Exercise not found with id: " + id);
        }
        exerciseRepository.delete(exerciseRes.get());

    }

}
