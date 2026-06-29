package com.touristvisa.touristservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TouristDTO {
    private Long touristId;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
}