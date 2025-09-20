package com.gamify.userservice.exception;

public sealed class UserserviceException extends RuntimeException permits UserNotFoundException, InvalidCredentialsException{
    public UserserviceException(String message) {
        super(message);
    }
}
