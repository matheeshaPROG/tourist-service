package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.EntryRecordDTO;
import com.touristvisa.touristservice.service.EntryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entry-records")
@RequiredArgsConstructor
public class EntryRecordController {

    private final EntryRecordService entryRecordService;

    @PostMapping("/tourist/{touristId}")
    public EntryRecordDTO createEntryRecord(@PathVariable Long touristId, @RequestBody EntryRecordDTO dto) {
        return entryRecordService.createEntryRecord(touristId, dto);
    }

    @GetMapping
    public List<EntryRecordDTO> getAllEntryRecords() {
        return entryRecordService.getAllEntryRecords();
    }

    @GetMapping("/{id}")
    public EntryRecordDTO getEntryRecordById(@PathVariable Long id) {
        return entryRecordService.getEntryRecordById(id);
    }

    @GetMapping("/tourist/{touristId}")
    public List<EntryRecordDTO> getEntryRecordsByTouristId(@PathVariable Long touristId) {
        return entryRecordService.getEntryRecordsByTouristId(touristId);
    }

    @DeleteMapping("/{id}")
    public void deleteEntryRecord(@PathVariable Long id) {
        entryRecordService.deleteEntryRecord(id);
    }
}