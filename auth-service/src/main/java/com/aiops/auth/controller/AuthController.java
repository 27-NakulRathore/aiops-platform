package com.aiops.auth.controller;

import com.aiops.auth.common.ApiResponse;
import com.aiops.auth.dto.LoginRequest;
import com.aiops.auth.dto.LoginResponse;
import com.aiops.auth.dto.RegisterRequest;
import com.aiops.auth.dto.UserProfileResponse;
import com.aiops.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication APIs",
        description = "User registration, login and profile management"
)
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(
//            @Valid @RequestBody RegisterRequest request) {
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(authService.register(request));
//    }
    @Operation(
            summary = "Register User",
            description = "Create new user account"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequest request) {

        String response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<String>builder()
                                .success(true)
                                .message("User registered successfully")
                                .data(response)
                                .build()
                );
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(
//            @Valid @RequestBody LoginRequest request) {
//
//        return ResponseEntity.ok(
//                authService.login(request)
//        );
//    }
    @Operation(
            summary = "Login User",
            description = "Authenticate user and generate JWT token"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .build()
        );
    }
    
//    @GetMapping("/me")
//    public ResponseEntity<String> me(
//            Authentication authentication) {
//
//        return ResponseEntity.ok(
//                authentication.getName()
//        );
//    }
    @Operation(
            summary = "Get Current User",
            description = "Returns logged in user profile"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> me() {

        UserProfileResponse response =
                authService.getCurrentUser();

        return ResponseEntity.ok(
                ApiResponse.<UserProfileResponse>builder()
                        .success(true)
                        .message("User profile fetched successfully")
                        .data(response)
                        .build()
        );
    }
}