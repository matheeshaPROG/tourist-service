package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.PassportDTO;
import com.touristvisa.touristservice.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public PassportDTO createPassport(@PathVariable Long touristId, @RequestBody PassportDTO dto) {
        return passportService.createPassport(touristId, dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public List<PassportDTO> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public PassportDTO getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @GetMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public List<PassportDTO> getPassportsByTouristId(@PathVariable Long touristId) {
        return passportService.getPassportsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public PassportDTO updatePassport(@PathVariable Long id, @PathVariable Long touristId, @RequestBody PassportDTO dto) {
        return passportService.updatePassport(id, touristId, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }
}