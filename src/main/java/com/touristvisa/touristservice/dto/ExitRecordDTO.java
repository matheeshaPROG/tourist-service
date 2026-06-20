package com.touristvisa.touristservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExitRecordDTO {
    private Long exitId;
    private String airport;
    private LocalDate exitDate;
    private TouristDTO tourist;
}