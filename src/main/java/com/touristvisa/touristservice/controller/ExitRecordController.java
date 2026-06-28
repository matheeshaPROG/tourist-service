package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.ExitRecordDTO;
import com.touristvisa.touristservice.service.ExitRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/exit-records")
@RequiredArgsConstructor
public class ExitRecordController {

    private final ExitRecordService exitRecordService;

    @PostMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public ExitRecordDTO createExitRecord(@PathVariable Long touristId, @RequestBody ExitRecordDTO dto) {
        return exitRecordService.createExitRecord(touristId, dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<ExitRecordDTO> getAllExitRecords() {
        return exitRecordService.getAllExitRecords();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public ExitRecordDTO getExitRecordById(@PathVariable Long id) {
        return exitRecordService.getExitRecordById(id);
    }

    @GetMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<ExitRecordDTO> getExitRecordsByTouristId(@PathVariable Long touristId) {
        return exitRecordService.getExitRecordsByTouristId(touristId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public void deleteExitRecord(@PathVariable Long id) {
        exitRecordService.deleteExitRecord(id);
    }
}