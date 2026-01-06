package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "found_items")
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
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private String contactNumber;
    
    private String imageUrl;
    
    public FoundItem() {
    }
    
    public FoundItem(Long id, String personName, String age, String gender, String description,
                    String location, LocalDate foundDate, String status, User reportedBy,
                    LocalDateTime createdAt, String contactNumber, String imageUrl) {
        this.id = id;
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.location = location;
        this.foundDate = foundDate;
        this.status = status;
        this.reportedBy = reportedBy;
        this.createdAt = createdAt;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "FoundItem{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", foundDate=" + foundDate +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}