package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public Passport createPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Passport getPassportById(Long id) {
        return passportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passport not found with id: " + id));
    }

    public List<Passport> getPassportsByTouristId(Long touristId) {
        return passportRepository.findByTouristId(touristId);
    }

    public Passport updatePassport(Long id, Passport updatedPassport) {
        Passport passport = getPassportById(id);
        passport.setPassportNumber(updatedPassport.getPassportNumber());
        passport.setIssueDate(updatedPassport.getIssueDate());
        passport.setExpiryDate(updatedPassport.getExpiryDate());
        passport.setTouristId(updatedPassport.getTouristId());
        return passportRepository.save(passport);
    }

    public void deletePassport(Long id) {
        passportRepository.deleteById(id);
    }
}