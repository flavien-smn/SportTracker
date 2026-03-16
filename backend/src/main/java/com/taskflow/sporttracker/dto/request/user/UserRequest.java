package com.taskflow.sporttracker.dto.request.user;

import com.taskflow.sporttracker.dto.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @Email @NotNull String email,
        @NotNull String password,
        @NotNull Role role) {
}
