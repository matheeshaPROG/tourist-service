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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String gender;

    private String email;
}