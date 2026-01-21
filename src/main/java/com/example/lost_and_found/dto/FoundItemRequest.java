package com.example.lost_and_found.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class FoundItemRequest {

    @NotBlank(message = "Person's name is required")
    private String personName;
    @NotNull(message = "Age is required")
    private Integer age;
    @NotBlank(message = "Gender is required")
    private String gender;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Location is required")
    private String location;
    @NotNull(message = "Found date is required")
    private LocalDate foundDate;
    @NotBlank(message = "Contact number is required")
    private String contactNumber;
    private String imageUrl;
    private String status; // Added status field

    public FoundItemRequest() {
    }

    // Updated constructor to include status
    public FoundItemRequest(
            String personName,
            Integer age,
            String gender,
            String description,
            String location,
            LocalDate foundDate,
            String contactNumber,
            String imageUrl,
            String status) {

        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.location = location;
        this.foundDate = foundDate;
        this.contactNumber = contactNumber;
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

    // New getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
