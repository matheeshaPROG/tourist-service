package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.entity.Tourist;
import com.touristvisa.touristservice.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @PostMapping
    public Tourist createTourist(@RequestBody Tourist tourist) {
        return touristService.createTourist(tourist);
    }

    @GetMapping
    public List<Tourist> getAllTourists() {
        return touristService.getAllTourists();
    }

    @GetMapping("/{id}")
    public Tourist getTouristById(@PathVariable Long id) {
        return touristService.getTouristById(id);
    }

    @PutMapping("/{id}")
    public Tourist updateTourist(@PathVariable Long id, @RequestBody Tourist tourist) {
        return touristService.updateTourist(id, tourist);
    }

    @DeleteMapping("/{id}")
    public void deleteTourist(@PathVariable Long id) {
        touristService.deleteTourist(id);
    }
}