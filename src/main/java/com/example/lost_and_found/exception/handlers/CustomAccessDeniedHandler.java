package com.example.lost_and_found.exception.handlers;

import com.example.lost_and_found.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse<Object> apiResponse = new ApiResponse<>(
                HttpServletResponse.SC_FORBIDDEN,
                "Access Denied: You do not have permission to access this resource.",
                null,
                LocalDateTime.now()
        );

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
