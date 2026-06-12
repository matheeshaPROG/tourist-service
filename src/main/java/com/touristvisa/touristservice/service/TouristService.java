package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    @Autowired
    private TouristRepository touristRepository;

    // Create a new tourist
    public Tourist createTourist(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    // Get all tourists
    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }

    // Get one tourist by ID
    public Tourist getTouristById(Long id) {
        return touristRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + id));
    }

    // Update tourist
    public Tourist updateTourist(Long id, Tourist updatedTourist) {
        Tourist tourist = getTouristById(id);
        tourist.setFirstName(updatedTourist.getFirstName());
        tourist.setLastName(updatedTourist.getLastName());
        tourist.setNationality(updatedTourist.getNationality());
        tourist.setDateOfBirth(updatedTourist.getDateOfBirth());
        tourist.setGender(updatedTourist.getGender());
        return touristRepository.save(tourist);
    }

    // Delete tourist
    public void deleteTourist(Long id) {
        touristRepository.deleteById(id);
    }
}