package com.gamify.userservice.service;

import com.gamify.userservice.dto.UserRequest;
import com.gamify.userservice.dto.UserResponse;
import com.gamify.userservice.entity.User;
import com.gamify.userservice.exception.InvalidCredentialsException;
import com.gamify.userservice.exception.UserNotFoundException;
import com.gamify.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse register(UserRequest request) {
        var user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password()); // ⚠️ plain now, will encrypt later
        user.setRole("USER");
        return toResponse(userRepository.save(user));
    }
    @Override
    public UserResponse login(String email, String password) {
        var user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        return toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList(); // Java 17+ toList()
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.isActive());
    }
}
