package com.aiops.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String username, String role);

    String extractUsername(String token);

    String extractRole(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}