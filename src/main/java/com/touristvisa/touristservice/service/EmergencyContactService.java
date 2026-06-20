package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.dto.EmergencyContactDTO;
import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.EmergencyContact;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.EmergencyContactRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyContactService {

    private final EmergencyContactRepository emergencyContactRepository;
    private final TouristRepository touristRepository;

    public EmergencyContactDTO createContact(Long touristId, EmergencyContactDTO dto) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        EmergencyContact contact = new EmergencyContact();
        contact.setName(dto.getName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setRelationship(dto.getRelationship());
        contact.setTourist(tourist);

        EmergencyContact saved = emergencyContactRepository.save(contact);
        return mapToDTO(saved);
    }

    public List<EmergencyContactDTO> getAllContacts() {
        return emergencyContactRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EmergencyContactDTO getContactById(Long id) {
        EmergencyContact contact = emergencyContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency contact not found with id: " + id));
        return mapToDTO(contact);
    }

    public List<EmergencyContactDTO> getContactsByTouristId(Long touristId) {
        return emergencyContactRepository.findByTourist_TouristId(touristId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EmergencyContactDTO updateContact(Long id, Long touristId, EmergencyContactDTO dto) {
        EmergencyContact contact = emergencyContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency contact not found with id: " + id));
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        contact.setTourist(tourist);
        contact.setName(dto.getName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setRelationship(dto.getRelationship());

        EmergencyContact updated = emergencyContactRepository.save(contact);
        return mapToDTO(updated);
    }

    public void deleteContact(Long id) {
        emergencyContactRepository.deleteById(id);
    }

    private EmergencyContactDTO mapToDTO(EmergencyContact contact) {
        EmergencyContactDTO dto = new EmergencyContactDTO();
        dto.setContactId(contact.getContactId());
        dto.setName(contact.getName());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setRelationship(contact.getRelationship());
        dto.setTourist(mapTouristToDTO(contact.getTourist()));
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