package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
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
    private String status;
    
    @Column(nullable = false)
    private LocalDateTime claimDate = LocalDateTime.now();
    
    private LocalDateTime resolvedDate;
    
    private String resolvedBy;
    
    public Claim() {
    }
    
    public Claim(Long id, LostItem lostItem, FoundItem foundItem, User user, String claimDescription,
                String status, LocalDateTime claimDate, LocalDateTime resolvedDate, String resolvedBy) {
        this.id = id;
        this.lostItem = lostItem;
        this.foundItem = foundItem;
        this.user = user;
        this.claimDescription = claimDescription;
        this.status = status;
        this.claimDate = claimDate;
        this.resolvedDate = resolvedDate;
        this.resolvedBy = resolvedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LostItem getLostItem() {
        return lostItem;
    }

    public void setLostItem(LostItem lostItem) {
        this.lostItem = lostItem;
    }

    public FoundItem getFoundItem() {
        return foundItem;
    }

    public void setFoundItem(FoundItem foundItem) {
        this.foundItem = foundItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDateTime claimDate) {
        this.claimDate = claimDate;
    }

    public LocalDateTime getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDateTime resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", claimDescription='" + claimDescription + '\'' +
                ", status='" + status + '\'' +
                ", claimDate=" + claimDate +
                ", resolvedDate=" + resolvedDate +
                ", resolvedBy='" + resolvedBy + '\'' +
                '}';
    }
}