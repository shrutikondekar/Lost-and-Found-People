package com.example.lost_and_found.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Kumbh Mela Lost & Found Management System");
        response.put("version", "1.0.0");
        response.put("status", "Running");
        response.put("message", "API is working successfully!");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Swagger UI", "/swagger-ui/index.html");
        endpoints.put("API Docs", "/v3/api-docs");
        endpoints.put("Register", "POST /api/auth/register");
        endpoints.put("Login", "POST /api/auth/login");
        endpoints.put("Lost Items (Public)", "GET /api/lost-items/public/all");
        endpoints.put("Found Items (Public)", "GET /api/found-items/public/all");
        
        response.put("available_endpoints", endpoints);
        response.put("documentation", "Use Postman or Swagger UI for testing");
        
        return response;
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is healthy");
        return response;
    }
}