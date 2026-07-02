package com.touristvisa.touristservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passport> passports;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmergencyContact> emergencyContacts;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntryRecord> entryRecords;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExitRecord> exitRecords;
}