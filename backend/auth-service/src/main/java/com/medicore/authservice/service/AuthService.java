package com.medicore.authservice.service;

import com.medicore.authservice.dto.LoginRequest;
import com.medicore.authservice.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}
