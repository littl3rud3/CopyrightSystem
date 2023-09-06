package com.example.licenseapp.controller;

import java.util.List;

import com.example.licenseapp.dto.RecordingDTO;
import com.example.licenseapp.service.RecordingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recordings")
@Tag(name = "Record Controller")
public class RecordingController {
    
    private final RecordingService recordingService;
    
    @GetMapping
    @Operation(summary = "All records")
    public List<RecordingDTO> getAll() {
        
        return recordingService.getAll();
    }
    
    @GetMapping("/{id:[\\d]+}")
    @Operation(summary = "Record by ID")
    public RecordingDTO get(@PathVariable long id) {
        
        return recordingService.getById(id);
    }
    
    @PostMapping
    @Operation(summary = "Add record")
    public void create(@RequestBody RecordingDTO request) {
        
        recordingService.create(request);
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    @Operation(summary = "Delete record")
    public void delete(@PathVariable long id) {
        
        recordingService.delete(id);
    }
    
    @PutMapping("/removeSinger/{id}")
    @Operation(summary = "Delete a song artist")
    public void removeSinger(@PathVariable long id) {
        
        recordingService.removeSinger(id);
    }
    
    @PutMapping
    @Operation(summary = "Edit record")
    public void update(@RequestBody RecordingDTO updatedRecording) {
        
        recordingService.update(updatedRecording);
    }
}
