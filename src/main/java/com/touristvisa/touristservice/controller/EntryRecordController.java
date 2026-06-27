package com.touristvisa.touristservice.controller;

import com.touristvisa.touristservice.dto.EntryRecordDTO;
import com.touristvisa.touristservice.service.EntryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<EntryRecordDTO> getAllEntryRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return entryRecordService.getAllEntryRecords(PageRequest.of(page, size));
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