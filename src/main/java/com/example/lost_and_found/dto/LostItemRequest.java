package com.example.lost_and_found.dto;

import java.time.LocalDate;

public class LostItemRequest {
    private String personName;
    private String age;
    private String gender;
    private String description;
    private String lastSeenLocation;
    private LocalDate lostDate;
    private String reporterName;
    private String reporterContact;
    private String imageUrl;
    
    public LostItemRequest() {
    }
    
    public LostItemRequest(String personName, String age, String gender, String description,
                          String lastSeenLocation, LocalDate lostDate, String reporterName,
                          String reporterContact, String imageUrl) {
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.lastSeenLocation = lastSeenLocation;
        this.lostDate = lostDate;
        this.reporterName = reporterName;
        this.reporterContact = reporterContact;
        this.imageUrl = imageUrl;
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
}