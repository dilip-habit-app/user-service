package com.gamify.userservice.exception;

public final class UserNotFoundException extends UserserviceException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
