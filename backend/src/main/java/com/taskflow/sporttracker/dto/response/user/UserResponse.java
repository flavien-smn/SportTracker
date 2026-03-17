package com.taskflow.sporttracker.dto.response.user;

import java.util.UUID;

import com.taskflow.sporttracker.dto.Role;

public record UserResponse(
        UUID id,
        String email,
        Role role) {
}
