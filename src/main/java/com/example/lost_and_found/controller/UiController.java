package com.example.lost_and_found.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    @GetMapping("/app")
    public String index() {
        return "index.html";
    }
}
