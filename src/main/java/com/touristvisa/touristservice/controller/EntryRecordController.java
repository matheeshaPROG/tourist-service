package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.EntryRecordDTO;
import com.touristvisa.touristservice.service.EntryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/entry-records")
@RequiredArgsConstructor
public class EntryRecordController {

    private final EntryRecordService entryRecordService;

    @PostMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public EntryRecordDTO createEntryRecord(@PathVariable Long touristId, @RequestBody EntryRecordDTO dto) {
        return entryRecordService.createEntryRecord(touristId, dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<EntryRecordDTO> getAllEntryRecords() {
        return entryRecordService.getAllEntryRecords();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public EntryRecordDTO getEntryRecordById(@PathVariable Long id) {
        return entryRecordService.getEntryRecordById(id);
    }

    @GetMapping("/tourist/{touristId}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE')")
    public List<EntryRecordDTO> getEntryRecordsByTouristId(@PathVariable Long touristId) {
        return entryRecordService.getEntryRecordsByTouristId(touristId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public void deleteEntryRecord(@PathVariable Long id) {
        entryRecordService.deleteEntryRecord(id);
    }
}