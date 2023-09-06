package com.example.licenseapp.controller;

import java.util.List;

import com.example.licenseapp.dto.CompanyDTO;
import com.example.licenseapp.service.CompanyService;
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
@Tag(name = "Company controller")
@RequestMapping("/api/companies")
public class CompanyController {
    
    private final CompanyService companyService;
    
    @GetMapping
    @Operation(summary = "All companies")
    public List<CompanyDTO> getAll() {
        
        return companyService.getAll();
    }
    
    @GetMapping("/{id:[\\d]+}")
    @Operation(summary = "Company by ID")
    public CompanyDTO get(@PathVariable long id) {
        
        return companyService.getById(id);
    }
    
    @PostMapping
    @Operation(summary = "Add new company")
    public CompanyDTO create(@RequestBody CompanyDTO newCompany) {
        
        return companyService.create(newCompany);
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    @Operation(summary = "Delete company")
    public void delete(@PathVariable long id) {
        companyService.delete(id);
    }
}
