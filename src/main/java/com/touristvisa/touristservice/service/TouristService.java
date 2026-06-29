package com.touristvisa.touristservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.touristvisa.touristservice.dto.HotelTouristViewDTO;
import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.entity.Passport;
import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.exception.ResourceNotFoundException;
import com.touristvisa.touristservice.repository.PassportRepository;
import com.touristvisa.touristservice.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;

@Service
@RequiredArgsConstructor
public class TouristService {

    private final TouristRepository touristRepository;
    private final PassportRepository passportRepository;
    private final RestTemplate restTemplate = createRestTemplate();

    private RestTemplate createRestTemplate() {
        RestTemplate template = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest servletRequest = attributes.getRequest();
                String authHeader = servletRequest.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    request.getHeaders().add("Authorization", authHeader);
                }
            }
            return execution.execute(request, body);
        };
        template.setInterceptors(Collections.singletonList(interceptor));
        return template;
    }

    public TouristDTO createTourist(TouristDTO dto) {
        Tourist tourist = new Tourist();
        tourist.setFirstName(dto.getFirstName());
        tourist.setLastName(dto.getLastName());
        tourist.setNationality(dto.getNationality());
        tourist.setDateOfBirth(dto.getDateOfBirth());
        tourist.setGender(dto.getGender());
        tourist.setEmail(dto.getEmail());

        Tourist saved = touristRepository.save(tourist);
        
        if (saved.getEmail() != null && !saved.getEmail().isEmpty()) {
            try {
                String emailUrl = "http://207.180.253.221:8081/api/v1/alerts/send-email"; 
                java.util.Map<String, String> emailRequest = new java.util.HashMap<>();
                emailRequest.put("to", saved.getEmail());
                emailRequest.put("subject", "Welcome to Smart Tourist Visa System");
                emailRequest.put("text", "Dear " + saved.getFirstName() + ",\n\nWelcome! Your tourist profile has been created successfully.");
                restTemplate.postForObject(emailUrl, emailRequest, String.class);
            } catch (Exception e) {
                System.err.println("Failed to send welcome email: " + e.getMessage());
            }
        }
        
        return mapToDTO(saved);
    }

    public List<TouristDTO> getAllTourists() {
        return touristRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TouristDTO getTouristById(Long id) {
        Tourist tourist = touristRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + id));
        return mapToDTO(tourist);
    }

    public TouristDTO updateTourist(Long id, TouristDTO dto) {
        Tourist tourist = touristRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + id));

        tourist.setFirstName(dto.getFirstName());
        tourist.setLastName(dto.getLastName());
        tourist.setNationality(dto.getNationality());
        tourist.setDateOfBirth(dto.getDateOfBirth());
        tourist.setGender(dto.getGender());
        tourist.setEmail(dto.getEmail());

        Tourist updated = touristRepository.save(tourist);
        return mapToDTO(updated);
    }

    public void deleteTourist(Long id) {
        touristRepository.deleteById(id);
    }

    public HotelTouristViewDTO getHotelTouristView(String passportNumber) {
        Passport passport = passportRepository.findByPassportNumber(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found"));
        
        Tourist tourist = passport.getTourist();
        String visaStatus = "No Visa Found";
        
        try {
            String url = "http://207.180.253.221:8085/api/visas/search/passport?passportId=" + passport.getPassportId() + "&page=0&size=10";
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            
            if (response != null) {
                JsonNode contentNode = response;
                // If it's a Spring Page, it has a "content" array
                if (response.has("content")) {
                    contentNode = response.get("content");
                }
                
                if (contentNode.isArray() && contentNode.size() > 0) {
                    java.util.List<String> statuses = new java.util.ArrayList<>();
                    for (JsonNode visa : contentNode) {
                        String status = visa.has("visaStatus") ? visa.get("visaStatus").asText() : 
                                       (visa.has("status") ? visa.get("status").asText() : "Valid");
                        String type = visa.has("visaType") ? visa.get("visaType").asText() : "";
                        
                        if (!type.isEmpty()) {
                            statuses.add(status + " (" + type + ")");
                        } else {
                            statuses.add(status);
                        }
                    }
                    visaStatus = String.join(", ", statuses);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to fetch visa status: " + e.getMessage());
            e.printStackTrace();
        }
        
        return new HotelTouristViewDTO(
                tourist.getTouristId(),
                tourist.getFirstName(),
                tourist.getLastName(),
                passportNumber,
                visaStatus
        );
    }


    private TouristDTO mapToDTO(Tourist tourist) {
        TouristDTO dto = new TouristDTO();
        dto.setTouristId(tourist.getTouristId());
        dto.setFirstName(tourist.getFirstName());
        dto.setLastName(tourist.getLastName());
        dto.setNationality(tourist.getNationality());
        dto.setDateOfBirth(tourist.getDateOfBirth());
        dto.setGender(tourist.getGender());
        dto.setEmail(tourist.getEmail());
        return dto;
    }
}