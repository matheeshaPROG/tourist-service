package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.PassportDTO;
import com.touristvisa.touristservice.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping("/tourist/{touristId}")
    public PassportDTO createPassport(@PathVariable Long touristId, @RequestBody PassportDTO dto) {
        return passportService.createPassport(touristId, dto);
    }

    @GetMapping
    public Page<PassportDTO> getAllPassports(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return passportService.getAllPassports(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public PassportDTO getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<PassportDTO> getPassportsByTouristId(@PathVariable Long touristId) {
        return passportService.getPassportsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    public PassportDTO updatePassport(@PathVariable Long id, @PathVariable Long touristId, @RequestBody PassportDTO dto) {
        return passportService.updatePassport(id, touristId, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }
}