package com.touristvisa.touristservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelTouristViewDTO {
    private Long touristId;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String visaStatus;
}
