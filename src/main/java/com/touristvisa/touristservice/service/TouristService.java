package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristDTO createTourist(TouristDTO dto) {
        Tourist tourist = new Tourist();
        tourist.setFirstName(dto.getFirstName());
        tourist.setLastName(dto.getLastName());
        tourist.setNationality(dto.getNationality());
        tourist.setDateOfBirth(dto.getDateOfBirth());
        tourist.setGender(dto.getGender());

        Tourist saved = touristRepository.save(tourist);
        return mapToDTO(saved);
    }

    public List<TouristDTO> getAllTourists() {
        return touristRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TouristDTO getTouristById(Long id) {
        Tourist tourist = touristRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + id));
        return mapToDTO(tourist);
    }

    public TouristDTO updateTourist(Long id, TouristDTO dto) {
        Tourist tourist = touristRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + id));

        tourist.setFirstName(dto.getFirstName());
        tourist.setLastName(dto.getLastName());
        tourist.setNationality(dto.getNationality());
        tourist.setDateOfBirth(dto.getDateOfBirth());
        tourist.setGender(dto.getGender());

        Tourist updated = touristRepository.save(tourist);
        return mapToDTO(updated);
    }

    public void deleteTourist(Long id) {
        touristRepository.deleteById(id);
    }

    // Helper method: converts an entity into a DTO. Reused by every method above.
    private TouristDTO mapToDTO(Tourist tourist) {
        TouristDTO dto = new TouristDTO();
        dto.setTouristId(tourist.getTouristId());
        dto.setFirstName(tourist.getFirstName());
        dto.setLastName(tourist.getLastName());
        dto.setNationality(tourist.getNationality());
        dto.setDateOfBirth(tourist.getDateOfBirth());
        dto.setGender(tourist.getGender());
        return dto;
    }
}