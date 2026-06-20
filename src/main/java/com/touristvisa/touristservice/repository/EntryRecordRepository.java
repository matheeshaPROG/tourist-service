package com.touristvisa.touristservice.repository;

import com.touristvisa.touristservice.entity.EntryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRecordRepository extends JpaRepository<EntryRecord, Long> {
    List<EntryRecord> findByTourist_TouristId(Long touristId);
}