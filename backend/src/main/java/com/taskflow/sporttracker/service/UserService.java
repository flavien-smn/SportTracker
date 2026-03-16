package com.taskflow.sporttracker.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskflow.sporttracker.dto.UserRequest;
import com.taskflow.sporttracker.dto.UserResponse;
import com.taskflow.sporttracker.entity.User;
import com.taskflow.sporttracker.exception.customException.ConflictException;
import com.taskflow.sporttracker.mapper.UserMapper;
import com.taskflow.sporttracker.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        Boolean exists = userRepository.existsByEmail(userRequest.getEmail());
        if (exists) {
            throw new ConflictException("Email already used");
        }
        var user = userMapper.toEntity(userRequest);
        var savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserResponse getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
