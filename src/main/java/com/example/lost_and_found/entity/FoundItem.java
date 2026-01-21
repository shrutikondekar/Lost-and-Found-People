package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "found_items")
@PrimaryKeyJoinColumn(name = "id")
public class FoundItem extends Item {
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(nullable = false)
    private String gender;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private LocalDate foundDate;
    
    private String contactNumber;
    
    public FoundItem() {
        super();
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

    @Override
    public String toString() {
        return "FoundItem{" +
                "id=" + getId() +
                ", personName='" + getPersonName() + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + getDescription() + '\'' +
                ", location='" + location + '\'' +
                ", foundDate=" + foundDate +
                ", status='" + getStatus() + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}