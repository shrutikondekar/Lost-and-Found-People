package com.example.lost_and_found.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
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
}