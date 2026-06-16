package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristService {

    private final TouristRepository touristRepository;

    public Tourist createTourist(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }

    public Tourist getTouristById(Long id) {
        return touristRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + id));
    }

    public Tourist updateTourist(Long id, Tourist updatedTourist) {
        Tourist tourist = getTouristById(id);
        tourist.setFirstName(updatedTourist.getFirstName());
        tourist.setLastName(updatedTourist.getLastName());
        tourist.setNationality(updatedTourist.getNationality());
        tourist.setDateOfBirth(updatedTourist.getDateOfBirth());
        tourist.setGender(updatedTourist.getGender());
        return touristRepository.save(tourist);
    }

    public void deleteTourist(Long id) {
        touristRepository.deleteById(id);
    }
}