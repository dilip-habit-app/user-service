package com.gamify.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserserviceException.class)
    public ResponseEntity<Map<String, String>> handleUserErrors(UserserviceException ex) {
        HttpStatus status;
        if (ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof InvalidCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
        } else {
            // fallback for any future subclasses
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
