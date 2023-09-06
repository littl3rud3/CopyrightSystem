package com.example.licenseapp.controller;

import java.util.List;

import com.example.licenseapp.dto.SingerDTO;
import com.example.licenseapp.service.SingerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Singer controller")
@RequestMapping("/api/singers")
public class SingerController {
    
    private final SingerService signerService;
    
    @GetMapping("/{id:[\\d]+}")
    @Operation(summary = "Singer by ID")
    public SingerDTO getById(@PathVariable long id) {
        
        return signerService.getById(id);
    }
    
    @GetMapping
    @Operation(summary = "All singers")
    public List<SingerDTO> getAll() {
        
        return signerService.getAll();
    }
    
    @PostMapping
    @Operation(summary = "Add singer")
    public void create(@RequestBody SingerDTO newSinger) {
        
        signerService.create(newSinger);
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    @Operation(summary = "Delete singer")
    public void delete(@PathVariable long id) {
        
        signerService.delete(id);
    }
}
