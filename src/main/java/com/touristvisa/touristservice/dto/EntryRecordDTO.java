package com.touristvisa.touristservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EntryRecordDTO {
    private Long entryId;
    private String airport;
    private LocalDate entryDate;
    private TouristDTO tourist;
}