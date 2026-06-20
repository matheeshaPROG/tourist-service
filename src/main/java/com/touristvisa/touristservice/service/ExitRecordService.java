package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.dto.ExitRecordDTO;
import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.ExitRecord;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.ExitRecordRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExitRecordService {

    private final ExitRecordRepository exitRecordRepository;
    private final TouristRepository touristRepository;

    public ExitRecordDTO createExitRecord(Long touristId, ExitRecordDTO dto) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        ExitRecord exitRecord = new ExitRecord();
        exitRecord.setAirport(dto.getAirport());
        exitRecord.setExitDate(dto.getExitDate());
        exitRecord.setTourist(tourist);

        ExitRecord saved = exitRecordRepository.save(exitRecord);
        return mapToDTO(saved);
    }

    public List<ExitRecordDTO> getAllExitRecords() {
        return exitRecordRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ExitRecordDTO getExitRecordById(Long id) {
        ExitRecord exitRecord = exitRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exit record not found with id: " + id));
        return mapToDTO(exitRecord);
    }

    public List<ExitRecordDTO> getExitRecordsByTouristId(Long touristId) {
        return exitRecordRepository.findByTourist_TouristId(touristId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteExitRecord(Long id) {
        exitRecordRepository.deleteById(id);
    }

    private ExitRecordDTO mapToDTO(ExitRecord exitRecord) {
        ExitRecordDTO dto = new ExitRecordDTO();
        dto.setExitId(exitRecord.getExitId());
        dto.setAirport(exitRecord.getAirport());
        dto.setExitDate(exitRecord.getExitDate());
        dto.setTourist(mapTouristToDTO(exitRecord.getTourist()));
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