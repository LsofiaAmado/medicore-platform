package com.medicore.users.dto.response;

import com.medicore.users.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
