package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lost_items")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personName;

    @Column(nullable = false)
    private Integer age;   // ✅ FIXED

    @Column(nullable = false)
    private String gender; // ✅ FIXED (MALE / FEMALE / OTHER)

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String lastSeenLocation;

    @Column(nullable = false)
    private LocalDate lostDate;

    @Column(nullable = false)
    private String reporterName;

    @Column(nullable = false)
    private String reporterContact;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "reported_by", nullable = false)
    private User reportedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String imageUrl;

    // ---------- Constructors ----------

    public LostItem() {
        this.createdAt = LocalDateTime.now();
    }

    public LostItem(Long id, String personName, Integer age, String gender,
                    String description, String lastSeenLocation,
                    LocalDate lostDate, String reporterName,
                    String reporterContact, String status,
                    User reportedBy, LocalDateTime createdAt,
                    String imageUrl) {
        this.id = id;
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.lastSeenLocation = lastSeenLocation;
        this.lostDate = lostDate;
        this.reporterName = reporterName;
        this.reporterContact = reporterContact;
        this.status = status;
        this.reportedBy = reportedBy;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.imageUrl = imageUrl;
    }

    // ---------- Getters & Setters ----------

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public String getLastSeenLocation() {
        return lastSeenLocation;
    }

    public void setLastSeenLocation(String lastSeenLocation) {
        this.lastSeenLocation = lastSeenLocation;
    }

    public LocalDate getLostDate() {
        return lostDate;
    }

    public void setLostDate(LocalDate lostDate) {
        this.lostDate = lostDate;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterContact() {
        return reporterContact;
    }

    public void setReporterContact(String reporterContact) {
        this.reporterContact = reporterContact;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // ---------- toString ----------

    @Override
    public String toString() {
        return "LostItem{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", lostDate=" + lostDate +
                '}';
    }
}
