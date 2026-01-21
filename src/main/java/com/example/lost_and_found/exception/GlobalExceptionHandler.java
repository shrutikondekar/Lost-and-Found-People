package com.example.lost_and_found.exception;

import com.example.lost_and_found.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.warn("Resource not found: {}", ex.getMessage());
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        logger.warn("Validation failed for request: {}", errors);
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        logger.warn("Access Denied: {} for request: {}", ex.getMessage(), request.getDescription(false));
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "Access Denied: You do not have permission to access this resource.", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        logger.warn("Authentication failed: {} for request: {}", ex.getMessage(), request.getDescription(false));
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: " + ex.getMessage(), null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An internal server error occurred. Please try again later.", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}