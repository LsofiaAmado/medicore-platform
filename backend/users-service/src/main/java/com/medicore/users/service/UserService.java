package com.medicore.users.service;

import com.medicore.users.dto.request.CreateUserRequest;
import com.medicore.users.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);
}
