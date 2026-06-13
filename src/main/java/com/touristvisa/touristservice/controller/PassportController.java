package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passports")
public class PassportController {

    @Autowired
    private PassportService passportService;

    @PostMapping
    public Passport createPassport(@RequestBody Passport passport) {
        return passportService.createPassport(passport);
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

    @PutMapping("/{id}")
    public Passport updatePassport(@PathVariable Long id, @RequestBody Passport passport) {
        return passportService.updatePassport(id, passport);
    }

    @DeleteMapping("/{id}")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }
}