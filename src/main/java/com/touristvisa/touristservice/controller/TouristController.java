package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.TouristDTO;
import com.touristvisa.touristservice.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourists")
@RequiredArgsConstructor
public class TouristController {

    private final TouristService touristService;

    @PostMapping
    public TouristDTO createTourist(@RequestBody TouristDTO dto) {
        return touristService.createTourist(dto);
    }

    @GetMapping
    public Page<TouristDTO> getAllTourists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return touristService.getAllTourists(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public TouristDTO getTouristById(@PathVariable Long id) {
        return touristService.getTouristById(id);
    }

    @PutMapping("/{id}")
    public TouristDTO updateTourist(@PathVariable Long id, @RequestBody TouristDTO dto) {
        return touristService.updateTourist(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTourist(@PathVariable Long id) {
        touristService.deleteTourist(id);
    }
}