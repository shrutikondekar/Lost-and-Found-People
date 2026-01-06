package com.example.lost_and_found.controller;

import com.example.lost_and_found.dto.ApiResponse;
import com.example.lost_and_found.entity.Claim;
import com.example.lost_and_found.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "*")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(new ApiResponse(true, "Claims retrieved successfully", claims));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.getClaimById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Claim retrieved successfully", claim));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> getClaimsByStatus(@PathVariable String status) {
        List<Claim> claims = claimService.getClaimsByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "Claims retrieved successfully", claims));
    }

    @GetMapping("/my-claims")
    public ResponseEntity<ApiResponse> getMyClaims() {
        List<Claim> claims = claimService.getMyClaims();
        return ResponseEntity.ok(new ApiResponse(true, "Your claims retrieved successfully", claims));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createClaim(@RequestBody Claim claim) {
        Claim createdClaim = claimService.createClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Claim created successfully", createdClaim));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'VOLUNTEER')")
    public ResponseEntity<ApiResponse> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam String resolvedBy) {
        Claim updatedClaim = claimService.updateClaimStatus(id, status, resolvedBy);
        return ResponseEntity.ok(new ApiResponse(true, "Claim status updated successfully", updatedClaim));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok(new ApiResponse(true, "Claim deleted successfully"));
    }
}