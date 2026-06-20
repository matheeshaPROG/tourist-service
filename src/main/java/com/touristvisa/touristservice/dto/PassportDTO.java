package com.touristvisa.touristservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PassportDTO {
    private Long passportId;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private TouristDTO tourist;
}