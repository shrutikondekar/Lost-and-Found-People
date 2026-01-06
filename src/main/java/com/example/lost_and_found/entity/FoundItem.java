package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "found_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoundItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String personName;
    
    @Column(nullable = false)
    private String age;
    
    @Column(nullable = false)
    private String gender;
    
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private LocalDate foundDate;
    
    @Column(nullable = false)
    private String status; // FOUND, CLAIMED, REUNITED
    
    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private String contactNumber;
    
    private String imageUrl;
}