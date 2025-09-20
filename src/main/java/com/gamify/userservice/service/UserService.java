package com.gamify.userservice.service;

import com.gamify.userservice.dto.UserRequest;
import com.gamify.userservice.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserResponse register(UserRequest userRequest);
    UserResponse login(String email, String password);
    UserResponse getUserById(Long id);
    List<UserResponse> getAll();
}
