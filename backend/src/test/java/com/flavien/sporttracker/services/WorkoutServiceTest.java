package com.flavien.sporttracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flavien.sporttracker.dto.response.workout.WorkoutDetailResponse;
import com.flavien.sporttracker.entity.User;
import com.flavien.sporttracker.entity.Workout;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.WorkoutMapper;
import com.flavien.sporttracker.repository.WorkoutRepository;
import com.flavien.sporttracker.service.UserService;
import com.flavien.sporttracker.service.WorkoutService;

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
         *      Scenario: Workout found and belongs to user
         *      Expected: WorkoutDetailResponse is returned with correct id
         */
        @Test
        void getById_shouldReturnWorkoutDetailResponse_whenWorkoutExists() {
                UUID workoutId = UUID.randomUUID();
                String email = "test@test.com";

                User user = User.builder()
                                .email(email)
                                .build();

                Workout workout = Workout.builder()
                                .id(workoutId)
                                .user(user)
                                .build();

                when(workoutRepository.findByIdWithExercices(workoutId))
                                .thenReturn(Optional.of(workout));

                WorkoutDetailResponse response = workoutService.getById(workoutId, email);

                assertEquals(workout.getId(), response.id());
        }

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
                String emailUser = "test@test.com";
                String emailGiven = "test2@test.com";

                User user = User.builder()
                                .email(emailUser)
                                .build();

                Workout workout = Workout.builder()
                                .id(id)
                                .user(user)
                                .build();

                when(workoutRepository.findByIdWithExercices(id))
                                .thenReturn(Optional.of(workout));
                // WHEN + THEN
                assertThrows(NotFoundException.class,
                                () -> workoutService.getById(id, emailGiven));
        }

}
