package com.flavien.sporttracker.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flavien.sporttracker.dto.request.user.UserRequest;
import com.flavien.sporttracker.dto.response.user.UserResponse;
import com.flavien.sporttracker.entity.User;
import com.flavien.sporttracker.exception.customException.ConflictException;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.mapper.UserMapper;
import com.flavien.sporttracker.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        Boolean exists = userRepository.existsByEmail(userRequest.email());
        if (exists) {
            throw new ConflictException("Email already used");
        }
        User user = User.builder()
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .build();

        var savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserResponse getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
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
