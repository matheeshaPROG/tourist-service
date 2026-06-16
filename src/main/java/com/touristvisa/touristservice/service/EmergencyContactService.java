package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.entity.EmergencyContact;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.repository.EmergencyContactRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyContactService {

    private final EmergencyContactRepository emergencyContactRepository;
    private final TouristRepository touristRepository;

    public EmergencyContact createContact(Long touristId, EmergencyContact contact) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + touristId));
        contact.setTourist(tourist);
        return emergencyContactRepository.save(contact);
    }

    public List<EmergencyContact> getAllContacts() {
        return emergencyContactRepository.findAll();
    }

    public EmergencyContact getContactById(Long id) {
        return emergencyContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emergency contact not found with id: " + id));
    }

    public List<EmergencyContact> getContactsByTouristId(Long touristId) {
        return emergencyContactRepository.findByTourist_TouristId(touristId);
    }

    public EmergencyContact updateContact(Long id, Long touristId, EmergencyContact updatedContact) {
        EmergencyContact contact = getContactById(id);
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + touristId));
        contact.setTourist(tourist);
        contact.setName(updatedContact.getName());
        contact.setPhoneNumber(updatedContact.getPhoneNumber());
        contact.setRelationship(updatedContact.getRelationship());
        return emergencyContactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        emergencyContactRepository.deleteById(id);
    }
}