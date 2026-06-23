package com.aiops.auth.serviceImpl;

import com.aiops.auth.dto.LoginRequest;
import com.aiops.auth.dto.LoginResponse;
import com.aiops.auth.dto.RegisterRequest;
import com.aiops.auth.dto.UserProfileResponse;
import com.aiops.auth.entity.User;
import com.aiops.auth.exception.ResourceAlreadyExistsException;
import com.aiops.auth.repository.UserRepository;
import com.aiops.auth.service.AuthService;
import com.aiops.auth.service.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
        	throw new ResourceAlreadyExistsException(
        	        "User already exists with email: " + request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
    
    @Override
    public UserProfileResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
}
