package com.medicore.authservice.service;

import com.medicore.authservice.client.UserClient;
import com.medicore.authservice.dto.LoginRequest;
import com.medicore.authservice.dto.LoginResponse;
import com.medicore.authservice.dto.UserResponse;
import com.medicore.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserClient userClient;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        UserResponse user =
                userClient.getUserForAuthentication(
                        request.getEmail()
                );

        if (!user.getActive()) {
            throw new IllegalArgumentException(
                    "User is inactive"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException(
                    "Invalid credentials"
            );
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .expiresIn(3600L)
                .build();
    }

}
