package com.flavien.sporttracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flavien.sporttracker.dto.auth.SignInResponse;
import com.flavien.sporttracker.dto.auth.SigninRequest;
import com.flavien.sporttracker.dto.auth.SignupRequest;
import com.flavien.sporttracker.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Register a new user with the provided signup details.
     * 
     * @param signupRequest The details of the user to register.
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
    }

    /**
     * Authenticate a user with the provided signin details and return a JWT token
     * if the authentication is successful.
     * 
     * @param signinRequest The details of the user to authenticate.
     * @return A SignInResponse containing the JWT token if authentication is
     *         successful.
     */
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signin(@Valid @RequestBody SigninRequest signinRequest) {
        return authService.signin(signinRequest);
    }

}
