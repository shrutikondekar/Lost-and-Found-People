package com.example.lost_and_found.service;

import com.example.lost_and_found.entity.Claim;
import com.example.lost_and_found.entity.User;
import com.example.lost_and_found.exception.ResourceNotFoundException;
import com.example.lost_and_found.repository.ClaimRepository;
import com.example.lost_and_found.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + id));
    }

    public List<Claim> getClaimsByStatus(String status) {
        return claimRepository.findByStatus(status);
    }

    public Claim createClaim(Claim claim) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        claim.setUser(user);
        claim.setStatus("PENDING");
        claim.setClaimDate(LocalDateTime.now());
        
        return claimRepository.save(claim);
    }

    public Claim updateClaimStatus(Long id, String status, String resolvedBy) {
        Claim claim = getClaimById(id);
        claim.setStatus(status);
        claim.setResolvedDate(LocalDateTime.now());
        claim.setResolvedBy(resolvedBy);
        
        return claimRepository.save(claim);
    }

    public void deleteClaim(Long id) {
        Claim claim = getClaimById(id);
        claimRepository.delete(claim);
    }

    public List<Claim> getMyC laims() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return claimRepository.findByUser(user);
    }
}