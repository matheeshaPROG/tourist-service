package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.entity.EmergencyContact;
import com.touristvisa.touristservice.service.EmergencyContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contacts")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    @PostMapping("/tourist/{touristId}")
    public EmergencyContact createContact(@PathVariable Long touristId, @RequestBody EmergencyContact contact) {
        return emergencyContactService.createContact(touristId, contact);
    }

    @GetMapping
    public List<EmergencyContact> getAllContacts() {
        return emergencyContactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public EmergencyContact getContactById(@PathVariable Long id) {
        return emergencyContactService.getContactById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<EmergencyContact> getContactsByTouristId(@PathVariable Long touristId) {
        return emergencyContactService.getContactsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    public EmergencyContact updateContact(@PathVariable Long id, @PathVariable Long touristId, @RequestBody EmergencyContact contact) {
        return emergencyContactService.updateContact(id, touristId, contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        emergencyContactService.deleteContact(id);
    }
}