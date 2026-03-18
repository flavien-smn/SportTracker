package com.flavien.sporttracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.exercise.ExerciseCreateRequest;
import com.flavien.sporttracker.dto.response.exercise.ExerciseListResponse;
import com.flavien.sporttracker.entity.Exercise;
import com.flavien.sporttracker.entity.User;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.ExerciseMapper;
import com.flavien.sporttracker.repository.ExerciseRepository;

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

    public Exercise getByIdAndAvailableForUser(UUID id, String email) {
        return exerciseRepository.findByIdAndAvailableForUser(id, email)
                .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + id));
    }

}
