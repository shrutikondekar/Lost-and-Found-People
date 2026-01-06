package com.example.lost_and_found.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}