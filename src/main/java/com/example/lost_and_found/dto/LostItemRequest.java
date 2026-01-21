package com.example.lost_and_found.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LostItemRequest {

    @NotBlank(message = "Person's name is required")
    private String personName;
    @NotNull(message = "Age is required")
    private Integer age;
    @NotBlank(message = "Gender is required")
    private String gender;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Last seen location is required")
    private String lastSeenLocation;
    @NotNull(message = "Lost date is required")
    private LocalDate lostDate;
    @NotBlank(message = "Reporter name is required")
    private String reporterName;
    @NotBlank(message = "Reporter contact is required")
    private String reporterContact;
    private String imageUrl;
    private String status; // Added status field

    // No-args constructor
    public LostItemRequest() {
    }

    // All-args constructor - updated to include status
    public LostItemRequest(String personName, Integer age, String gender,
                           String description, String lastSeenLocation,
                           LocalDate lostDate, String reporterName,
                           String reporterContact, String imageUrl, String status) {
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.lastSeenLocation = lastSeenLocation;
        this.lostDate = lostDate;
        this.reporterName = reporterName;
        this.reporterContact = reporterContact;
        this.imageUrl = imageUrl;
        this.status = status;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // New getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
