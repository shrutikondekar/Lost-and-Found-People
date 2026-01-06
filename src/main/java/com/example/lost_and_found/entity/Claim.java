package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "lost_item_id")
    private LostItem lostItem;
    
    @ManyToOne
    @JoinColumn(name = "found_item_id")
    private FoundItem foundItem;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(nullable = false)
    private String claimDescription;
    
    @Column(nullable = false)
    private String status; // PENDING, APPROVED, REJECTED
    
    @Column(nullable = false)
    private LocalDateTime claimDate = LocalDateTime.now();
    
    private LocalDateTime resolvedDate;
    
    private String resolvedBy;
}