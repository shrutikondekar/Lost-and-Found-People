package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.entity.Item;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:63342")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        ApiResponse<List<User>> response = new ApiResponse<>(200, "Successfully retrieved all users.", users, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items")
    public ResponseEntity<ApiResponse<List<Item>>> getAllItems() {
        List<Item> items = adminService.getAllItems();
        ApiResponse<List<Item>> response = new ApiResponse<>(200, "Successfully retrieved all items.", items, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/items/{id}/resolve")
    public ResponseEntity<ApiResponse<Item>> resolveItem(@PathVariable Long id) {
        Item resolvedItem = adminService.resolveItem(id);
        ApiResponse<Item> response = new ApiResponse<>(200, "Item successfully resolved.", resolvedItem, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}