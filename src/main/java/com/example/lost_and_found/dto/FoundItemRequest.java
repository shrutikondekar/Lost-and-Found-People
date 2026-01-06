package com.example.lost_and_found.dto;

import java.time.LocalDate;

public class FoundItemRequest {
    private String personName;
    private String age;
    private String gender;
    private String description;
    private String location;
    private LocalDate foundDate;
    private String contactNumber;
    private String imageUrl;
    
    public FoundItemRequest() {
    }
    
    public FoundItemRequest(String personName, String age, String gender, String description,
                           String location, LocalDate foundDate, String contactNumber, String imageUrl) {
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.location = location;
        this.foundDate = foundDate;
        this.contactNumber = contactNumber;
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
}