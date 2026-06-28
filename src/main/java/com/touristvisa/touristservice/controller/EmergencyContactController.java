package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.EmergencyContactDTO;
import com.touristvisa.touristservice.service.EmergencyContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contacts")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    @PostMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public EmergencyContactDTO createContact(@PathVariable Long touristId, @RequestBody EmergencyContactDTO dto) {
        return emergencyContactService.createContact(touristId, dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<EmergencyContactDTO> getAllContacts() {
        return emergencyContactService.getAllContacts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public EmergencyContactDTO getContactById(@PathVariable Long id) {
        return emergencyContactService.getContactById(id);
    }

    @GetMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<EmergencyContactDTO> getContactsByTouristId(@PathVariable Long touristId) {
        return emergencyContactService.getContactsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public EmergencyContactDTO updateContact(@PathVariable Long id, @PathVariable Long touristId, @RequestBody EmergencyContactDTO dto) {
        return emergencyContactService.updateContact(id, touristId, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public void deleteContact(@PathVariable Long id) {
        emergencyContactService.deleteContact(id);
    }
}