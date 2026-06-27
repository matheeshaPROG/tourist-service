package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.EmergencyContactDTO;
import com.touristvisa.touristservice.service.EmergencyContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contacts")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    @PostMapping("/tourist/{touristId}")
    public EmergencyContactDTO createContact(@PathVariable Long touristId, @RequestBody EmergencyContactDTO dto) {
        return emergencyContactService.createContact(touristId, dto);
    }

    @GetMapping
    public Page<EmergencyContactDTO> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return emergencyContactService.getAllContacts(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public EmergencyContactDTO getContactById(@PathVariable Long id) {
        return emergencyContactService.getContactById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<EmergencyContactDTO> getContactsByTouristId(@PathVariable Long touristId) {
        return emergencyContactService.getContactsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    public EmergencyContactDTO updateContact(@PathVariable Long id, @PathVariable Long touristId, @RequestBody EmergencyContactDTO dto) {
        return emergencyContactService.updateContact(id, touristId, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        emergencyContactService.deleteContact(id);
    }
}