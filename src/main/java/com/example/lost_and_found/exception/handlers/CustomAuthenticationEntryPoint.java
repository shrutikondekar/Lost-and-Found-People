package com.example.lost_and_found.exception.handlers;

import com.example.lost_and_found.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationEntryPoint() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiResponse<Object> apiResponse = new ApiResponse<>(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized: " + authException.getMessage(),
                null,
                LocalDateTime.now()
        );

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
