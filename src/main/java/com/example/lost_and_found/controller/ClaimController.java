package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.entity.Claim;
import com.example.lost_and_found.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "*")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<List<Claim>>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        ApiResponse<List<Claim>> response = new ApiResponse<>(HttpStatus.OK.value(), "Claims retrieved successfully", claims, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Claim>> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.getClaimById(id);
        ApiResponse<Claim> response = new ApiResponse<>(HttpStatus.OK.value(), "Claim retrieved successfully", claim, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<List<Claim>>> getClaimsByStatus(@PathVariable String status) {
        List<Claim> claims = claimService.getClaimsByStatus(status);
        ApiResponse<List<Claim>> response = new ApiResponse<>(HttpStatus.OK.value(), "Claims retrieved successfully", claims, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-claims")
    public ResponseEntity<ApiResponse<List<Claim>>> getMyClaims() {
        List<Claim> claims = claimService.getMyClaims();
        ApiResponse<List<Claim>> response = new ApiResponse<>(HttpStatus.OK.value(), "Your claims retrieved successfully", claims, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Claim>> createClaim(@RequestBody Claim claim) {
        Claim createdClaim = claimService.createClaim(claim);
        ApiResponse<Claim> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Claim created successfully", createdClaim, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse<Claim>> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam String resolvedBy) {
        Claim updatedClaim = claimService.updateClaimStatus(id, status, resolvedBy);
        ApiResponse<Claim> response = new ApiResponse<>(HttpStatus.OK.value(), "Claim status updated successfully", updatedClaim, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Claim deleted successfully", null, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}