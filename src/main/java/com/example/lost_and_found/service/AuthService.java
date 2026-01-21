package com.example.lost_and_found.service;

import com.example.lost_and_found.dto.LoginRequest;
import com.example.lost_and_found.dto.RegisterRequest;
import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.repository.UserRepository;
import com.example.lost_and_found.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public ApiResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            logger.warn("Registration failed: Username '{}' already exists.", request.getUsername());
            return new ApiResponse(400, "Username already exists", null, java.time.LocalDateTime.now());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            logger.warn("Registration failed: Email '{}' already exists.", request.getEmail());
            return new ApiResponse(400, "Email already exists", null, java.time.LocalDateTime.now());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        user.setEnabled(true);

        userRepository.save(user);
        logger.info("User '{}' registered successfully.", user.getUsername());

        return new ApiResponse(200, "User registered successfully", null, java.time.LocalDateTime.now());
    }

    public ApiResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getUsername());
            
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());
            data.put("role", user.getRole());

            logger.info("User '{}' logged in successfully.", user.getUsername());
            return new ApiResponse(200, "Login successful", data, java.time.LocalDateTime.now());
        } catch (Exception e) {
            logger.warn("Login failed for user '{}': Invalid credentials.", request.getUsername());
            return new ApiResponse(401, "Invalid credentials", null, java.time.LocalDateTime.now());
        }
    }

    public User getMe(UserDetails userDetails) {
        logger.info("Fetching details for user '{}'.", userDetails.getUsername());
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}


    