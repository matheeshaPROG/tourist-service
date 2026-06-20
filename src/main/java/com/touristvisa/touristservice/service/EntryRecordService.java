package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.dto.EntryRecordDTO;
import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.EntryRecord;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.EntryRecordRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryRecordService {

    private final EntryRecordRepository entryRecordRepository;
    private final TouristRepository touristRepository;

    public EntryRecordDTO createEntryRecord(Long touristId, EntryRecordDTO dto) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        EntryRecord entryRecord = new EntryRecord();
        entryRecord.setAirport(dto.getAirport());
        entryRecord.setEntryDate(dto.getEntryDate());
        entryRecord.setTourist(tourist);

        EntryRecord saved = entryRecordRepository.save(entryRecord);
        return mapToDTO(saved);
    }

    public List<EntryRecordDTO> getAllEntryRecords() {
        return entryRecordRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EntryRecordDTO getEntryRecordById(Long id) {
        EntryRecord entryRecord = entryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entry record not found with id: " + id));
        return mapToDTO(entryRecord);
    }

    public List<EntryRecordDTO> getEntryRecordsByTouristId(Long touristId) {
        return entryRecordRepository.findByTourist_TouristId(touristId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteEntryRecord(Long id) {
        entryRecordRepository.deleteById(id);
    }

    private EntryRecordDTO mapToDTO(EntryRecord entryRecord) {
        EntryRecordDTO dto = new EntryRecordDTO();
        dto.setEntryId(entryRecord.getEntryId());
        dto.setAirport(entryRecord.getAirport());
        dto.setEntryDate(entryRecord.getEntryDate());
        dto.setTourist(mapTouristToDTO(entryRecord.getTourist()));
        return dto;
    }

    private TouristDTO mapTouristToDTO(Tourist tourist) {
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setTouristId(tourist.getTouristId());
        touristDTO.setFirstName(tourist.getFirstName());
        touristDTO.setLastName(tourist.getLastName());
        touristDTO.setNationality(tourist.getNationality());
        touristDTO.setDateOfBirth(tourist.getDateOfBirth());
        touristDTO.setGender(tourist.getGender());
        return touristDTO;
    }
}