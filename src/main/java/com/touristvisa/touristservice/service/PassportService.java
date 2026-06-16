package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.repository.PassportRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportService {

    private final PassportRepository passportRepository;
    private final TouristRepository touristRepository;

    public Passport createPassport(Long touristId, Passport passport) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + touristId));
        passport.setTourist(tourist);
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
        return passportRepository.findByTourist_TouristId(touristId);
    }

    public Passport updatePassport(Long id, Long touristId, Passport updatedPassport) {
        Passport passport = getPassportById(id);
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("Tourist not found with id: " + touristId));
        passport.setTourist(tourist);
        passport.setPassportNumber(updatedPassport.getPassportNumber());
        passport.setIssueDate(updatedPassport.getIssueDate());
        passport.setExpiryDate(updatedPassport.getExpiryDate());
        return passportRepository.save(passport);
    }

    public void deletePassport(Long id) {
        passportRepository.deleteById(id);
    }
}