package com.medicore.authservice.client;

import com.medicore.authservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface UserClient {

    @GetMapping("/api/users/auth/{email}")
    UserResponse getUserForAuthentication(
            @PathVariable("email") String email
    );

}
