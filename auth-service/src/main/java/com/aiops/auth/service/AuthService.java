package com.aiops.auth.service;

import com.aiops.auth.dto.LoginRequest;
import com.aiops.auth.dto.LoginResponse;
import com.aiops.auth.dto.RegisterRequest;
import com.aiops.auth.dto.UserProfileResponse;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
    
    UserProfileResponse getCurrentUser();
}