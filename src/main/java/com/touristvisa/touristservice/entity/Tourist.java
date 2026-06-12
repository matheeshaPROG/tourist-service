package com.touristvisa.touristservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "tourists")
@Data
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long touristId;

    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private String gender;
}