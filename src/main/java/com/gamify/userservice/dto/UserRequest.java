package com.gamify.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Username is required") @Email String email,
        @NotBlank(message = "Username is required") String password
) {
}
