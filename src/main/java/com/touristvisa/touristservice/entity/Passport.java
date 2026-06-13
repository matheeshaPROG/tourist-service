package com.touristvisa.touristservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "passports")
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passportId;

    private Long touristId;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}