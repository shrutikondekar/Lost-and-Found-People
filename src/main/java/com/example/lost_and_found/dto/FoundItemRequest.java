package com.example.lost_and_found.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FoundItemRequest {
    private String personName;
    private String age;
    private String gender;
    private String description;
    private String location;
    private LocalDate foundDate;
    private String contactNumber;
    private String imageUrl;
}