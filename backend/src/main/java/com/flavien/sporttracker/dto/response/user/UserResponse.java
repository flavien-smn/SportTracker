package com.flavien.sporttracker.dto.response.user;

import java.util.UUID;

import com.flavien.sporttracker.dto.Role;

public record UserResponse(
                UUID id,
                String email,
                Role role) {
}
