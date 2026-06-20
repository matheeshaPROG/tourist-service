package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.ExitRecordDTO;
import com.touristvisa.touristservice.service.ExitRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exit-records")
@RequiredArgsConstructor
public class ExitRecordController {

    private final ExitRecordService exitRecordService;

    @PostMapping("/tourist/{touristId}")
    public ExitRecordDTO createExitRecord(@PathVariable Long touristId, @RequestBody ExitRecordDTO dto) {
        return exitRecordService.createExitRecord(touristId, dto);
    }

    @GetMapping
    public List<ExitRecordDTO> getAllExitRecords() {
        return exitRecordService.getAllExitRecords();
    }

    @GetMapping("/{id}")
    public ExitRecordDTO getExitRecordById(@PathVariable Long id) {
        return exitRecordService.getExitRecordById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<ExitRecordDTO> getExitRecordsByTouristId(@PathVariable Long touristId) {
        return exitRecordService.getExitRecordsByTouristId(touristId);
    }

    @DeleteMapping("/{id}")
    public void deleteExitRecord(@PathVariable Long id) {
        exitRecordService.deleteExitRecord(id);
    }
}