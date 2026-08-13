package com.medicore.users.service;

import com.medicore.users.dto.request.CreateUserRequest;
import com.medicore.users.dto.response.UserAuthResponse;
import com.medicore.users.dto.response.UserResponse;
import com.medicore.users.dto.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    UserResponse getUserByEmail(String email);

    UserAuthResponse getUserForAuthentication(String email);

}
