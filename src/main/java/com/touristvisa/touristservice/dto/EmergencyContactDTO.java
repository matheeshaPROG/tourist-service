package com.touristvisa.touristservice.dto;

import lombok.Data;

@Data
public class EmergencyContactDTO {
    private Long contactId;
    private String name;
    private String phoneNumber;
    private String relationship;
    private TouristDTO tourist;
}