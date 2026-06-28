package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/tourists")
@RequiredArgsConstructor
public class TouristController {

    private final TouristService touristService;

    @PostMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public TouristDTO createTourist(@RequestBody TouristDTO dto) {
        return touristService.createTourist(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public List<TouristDTO> getAllTourists() {
        return touristService.getAllTourists();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public TouristDTO getTouristById(@PathVariable Long id) {
        return touristService.getTouristById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public TouristDTO updateTourist(@PathVariable Long id, @RequestBody TouristDTO dto) {
        return touristService.updateTourist(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public void deleteTourist(@PathVariable Long id) {
        touristService.deleteTourist(id);
    }

    @GetMapping("/hotel-view")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF', 'TOURIST_POLICE')")
    public com.touristvisa.touristservice.dto.HotelTouristViewDTO getHotelTouristView(@RequestParam String passportNumber) {
        return touristService.getHotelTouristView(passportNumber);
    }
}