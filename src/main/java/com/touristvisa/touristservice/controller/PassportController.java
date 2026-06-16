package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping("/tourist/{touristId}")
    public Passport createPassport(@PathVariable Long touristId, @RequestBody Passport passport) {
        return passportService.createPassport(touristId, passport);
    }

    @GetMapping
    public List<Passport> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/{id}")
    public Passport getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<Passport> getPassportsByTouristId(@PathVariable Long touristId) {
        return passportService.getPassportsByTouristId(touristId);
    }

    @PutMapping("/{id}/tourist/{touristId}")
    public Passport updatePassport(@PathVariable Long id, @PathVariable Long touristId, @RequestBody Passport passport) {
        return passportService.updatePassport(id, touristId, passport);
    }

    @DeleteMapping("/{id}")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }
}