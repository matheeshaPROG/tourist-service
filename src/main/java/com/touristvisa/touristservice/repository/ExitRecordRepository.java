package com.touristvisa.touristservice.repository;

import com.touristvisa.touristservice.entity.ExitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExitRecordRepository extends JpaRepository<ExitRecord, Long> {
    List<ExitRecord> findByTourist_TouristId(Long touristId);
}