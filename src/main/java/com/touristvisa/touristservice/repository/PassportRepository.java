package com.touristvisa.touristservice.repository;

import com.touristvisa.touristservice.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    List<Passport> findByTourist_TouristId(Long touristId);
    Optional<Passport> findByPassportNumber(String passportNumber);
}