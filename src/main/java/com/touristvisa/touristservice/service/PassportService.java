package com.touristvisa.touristservice.service;

import com.touristvisa.touristservice.dto.PassportDTO;
import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.PassportRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PassportService {

    private final PassportRepository passportRepository;
    private final TouristRepository touristRepository;

    public PassportDTO createPassport(Long touristId, PassportDTO dto) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        Passport passport = new Passport();
        passport.setPassportNumber(dto.getPassportNumber());
        passport.setIssueDate(dto.getIssueDate());
        passport.setExpiryDate(dto.getExpiryDate());
        passport.setTourist(tourist);

        Passport saved = passportRepository.save(passport);
        return mapToDTO(saved);
    }

    public Page<PassportDTO> getAllPassports(Pageable pageable) {
        return passportRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public PassportDTO getPassportById(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found with id: " + id));
        return mapToDTO(passport);
    }

    public List<PassportDTO> getPassportsByTouristId(Long touristId) {
        return passportRepository.findByTourist_TouristId(touristId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PassportDTO updatePassport(Long id, Long touristId, PassportDTO dto) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found with id: " + id));
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + touristId));

        passport.setTourist(tourist);
        passport.setPassportNumber(dto.getPassportNumber());
        passport.setIssueDate(dto.getIssueDate());
        passport.setExpiryDate(dto.getExpiryDate());

        Passport updated = passportRepository.save(passport);
        return mapToDTO(updated);
    }

    public void deletePassport(Long id) {
        passportRepository.deleteById(id);
    }

    // Converts a Passport entity into a PassportDTO, including its nested TouristDTO
    private PassportDTO mapToDTO(Passport passport) {
        PassportDTO dto = new PassportDTO();
        dto.setPassportId(passport.getPassportId());
        dto.setPassportNumber(passport.getPassportNumber());
        dto.setIssueDate(passport.getIssueDate());
        dto.setExpiryDate(passport.getExpiryDate());
        dto.setTourist(mapTouristToDTO(passport.getTourist()));
        return dto;
    }

    // Small helper just for converting the nested Tourist
    private TouristDTO mapTouristToDTO(Tourist tourist) {
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setTouristId(tourist.getTouristId());
        touristDTO.setFirstName(tourist.getFirstName());
        touristDTO.setLastName(tourist.getLastName());
        touristDTO.setNationality(tourist.getNationality());
        touristDTO.setDateOfBirth(tourist.getDateOfBirth());
        touristDTO.setGender(tourist.getGender());
        return touristDTO;
    }
}