package com.taskflow.demo.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskflow.sporttracker.exception.customException.NotFoundException;
import com.taskflow.sporttracker.mapper.WorkoutMapper;
import com.taskflow.sporttracker.repository.WorkoutRepository;
import com.taskflow.sporttracker.service.UserService;
import com.taskflow.sporttracker.service.WorkoutService;

@ExtendWith(MockitoExtension.class)
public class WorkoutServiceTest {

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private UserService userService;

    @Mock
    private WorkoutMapper workoutMapper;

    @InjectMocks
    private WorkoutService workoutService;

    /**
     * @see WorkoutService#getById(UUID, String)
     *      Test for getById method in WorkoutService
     *      Scenario: Workout not found
     *      Expected: NotFoundException is thrown
     */
    @Test
    void getById_shouldThrowNotFoundException_whenWorkoutNotFound() {
        // GIVEN
        UUID id = UUID.randomUUID();
        when(workoutRepository.findByIdWithExercices(id))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(NotFoundException.class,
                () -> workoutService.getById(id, "test@test.com"));
    }

    /**
     * @see WorkoutService#getById(UUID, String)
     *      Test for getById method in WorkoutService
     *      Scenario: Workout does not belong to user
     *      Expected: NotFoundException is thrown
     */
    @Test
    void getById_shouldThrowNotFoundException_whenWorkoutDoesNotBelongToUser() {
        UUID id = UUID.randomUUID();
        
        when()
    }

}
