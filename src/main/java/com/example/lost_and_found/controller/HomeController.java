package com.example.lost_and_found.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller // Changed from @RestController
public class HomeController {

    @GetMapping("/")
    public RedirectView redirectToLogin() {
        return new RedirectView("/login.html");
    }
    
    @GetMapping("/health")
    @ResponseBody // Added @ResponseBody to keep it as an API endpoint
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is healthy");
        return response;
    }
}