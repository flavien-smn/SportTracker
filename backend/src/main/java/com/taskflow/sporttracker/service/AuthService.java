package com.taskflow.sporttracker.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.taskflow.sporttracker.dto.Role;
import com.taskflow.sporttracker.dto.auth.SignInResponse;
import com.taskflow.sporttracker.dto.auth.SigninRequest;
import com.taskflow.sporttracker.dto.auth.SignupRequest;
import com.taskflow.sporttracker.dto.request.user.UserRequest;
import com.taskflow.sporttracker.dto.response.user.UserResponse;
import com.taskflow.sporttracker.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public UserResponse signup(SignupRequest signupRequest) {
        return userService.create(new UserRequest(signupRequest.email(), signupRequest.password(), Role.USER));
    }

    public SignInResponse signin(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequest.email(),
                        signinRequest.password()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userService.getByEmail(userDetails.getUsername());

        String token = jwtService.generateToken(user);

        return new SignInResponse(token);
    }
}
