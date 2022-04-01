package com.example.licenseapp.controller;

import com.example.licenseapp.dto.CompanyDTO;
import com.example.licenseapp.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Tag(name = "Контроллер компаний")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Список всех компания")
    @GetMapping
    public List<CompanyDTO> getAll() {

        return companyService.getAll();
    }

    @Operation(summary = "Компания по идентификатору")
    @GetMapping("/{id:[\\d]+}")
    public CompanyDTO get(@PathVariable long id) {

        return companyService.getById(id);
    }

    @Operation(summary = "Добавить новую компанию")
    @PostMapping
    public CompanyDTO create(@RequestBody CompanyDTO newCompany) {

        return companyService.create(newCompany);
    }

    @Operation(summary = "Удалить компанию по идентификатору")
    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable long id) {
        companyService.delete(id);
    }
}
