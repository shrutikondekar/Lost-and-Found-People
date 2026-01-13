package com.example.lost_and_found.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/test")
    public Map<String, String> test() {
        Map<String, String> res = new HashMap<>();
        res.put("status", "OK");
        res.put("message", "Admin endpoint is alive (no auth required)");
        return res;
    }
}
