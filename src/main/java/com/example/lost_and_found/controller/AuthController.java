package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.dto.LoginRequest;
import com.example.lost_and_found.dto.RegisterRequest;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse response = authService.register(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest request) {
        ApiResponse response = authService.login(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        User user = authService.getMe(userDetails);
        ApiResponse<User> response = new ApiResponse<>(200, "Successfully retrieved user details.", user, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}