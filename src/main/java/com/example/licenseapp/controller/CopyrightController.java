package com.example.licenseapp.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.licenseapp.dto.CopyrightDTO;
import com.example.licenseapp.model.Copyright;
import com.example.licenseapp.service.CopyrightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Copyright Controller")
@RequestMapping("/api/copyrights")
public class CopyrightController {
    
    private final CopyrightService copyrightService;
    
    @Operation(summary = "All copyrights")
    @GetMapping
    public Page<Copyright> getAll(Pageable pageable) {
        
        return copyrightService.getAll(pageable);
    }
    
    @Operation(summary = "Copyright by ID")
    @GetMapping("/{id:[\\d]+}")
    public CopyrightDTO get(@PathVariable long id) {
        
        return copyrightService.getById(id);
    }
    
    @Operation(summary = "Add new copyright")
    @PostMapping
    public CopyrightDTO create(@RequestBody CopyrightDTO newCopyright) {
        
        return copyrightService.create(newCopyright);
    }
    
    @Operation(summary = "Delete copyright")
    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable long id) {
        
        copyrightService.delete(id);
    }
    
    @Operation(summary = "Find copyright in a span of dates")
    @GetMapping("/date")
    public List<CopyrightDTO> copyrightsByDates(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        
        return copyrightService.copyrightsByDates(from, to);
    }
    
    @Operation(summary = "Find Copyright by Company name")
    @GetMapping("/findByCompany")
    public List<CopyrightDTO> copyrightByCompanyName(@RequestParam String name) {
        
        return copyrightService.copyrightByCompanyName(name);
    }
}
