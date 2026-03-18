package com.flavien.sporttracker.service;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.flavien.sporttracker.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public String generateToken(User user) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("taskflow-api") // optionnel, mais bonne pratique
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600)) // 1h
                .claim("role", user.getRole().name()) // ou user.getRoles() si multiple
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
