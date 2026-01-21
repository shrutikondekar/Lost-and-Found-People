package com.example.lost_and_found.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lost_items")
@PrimaryKeyJoinColumn(name = "id")
public class LostItem extends Item {

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String lastSeenLocation;

    @Column(nullable = false)
    private LocalDate lostDate;

    @Column(nullable = false)
    private String reporterName;

    @Column(nullable = false)
    private String reporterContact;

    // ---------- Constructors ----------

    public LostItem() {
        super();
    }

    // ---------- Getters & Setters ----------

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


    // ---------- toString ----------

    @Override
    public String toString() {
        return "LostItem{" +
                "id=" + getId() +
                ", personName='" + getPersonName() + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", status='" + getStatus() + '\'' +
                ", lostDate=" + lostDate +
                '}';
    }
}
