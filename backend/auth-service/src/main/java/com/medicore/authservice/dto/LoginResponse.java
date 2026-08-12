package com.medicore.authservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String token;

    private String type;

    private Long expiresIn;

}
