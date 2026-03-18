package com.flavien.sporttracker.dto.request.user;

import com.flavien.sporttracker.dto.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
                @Email @NotNull String email,
                @NotNull String password,
                @NotNull Role role) {
}
