package com.medicore.users.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthResponse {

    private Long id;

    private String email;

    private String password;

    private String role;

    private Boolean active;
}
