package com.example.licenseapp.controller;

import com.example.licenseapp.dto.SingerDTO;
import com.example.licenseapp.service.SingerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/singers")
@RequiredArgsConstructor
@Tag(name = "Контроллер исполнителей")
public class SingerController {

    private final SingerService signerService;

    @Operation(summary = "Исполнитель по идентификатору")
    @GetMapping("/{id:[\\d]+}")
    public SingerDTO getById(@PathVariable long id) {

        return signerService.getById(id);
    }

    @Operation(summary = "Все исполнители")
    @GetMapping
    public List<SingerDTO> getAll() {

        return signerService.getAll();
    }

    @Operation(summary = "Добавить исполнителя")
    @PostMapping
    public void create(@RequestBody SingerDTO newSinger) {

        signerService.create(newSinger);
    }

    @Operation(summary = "Удалить исполнителя по идентификатору")
    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable long id) {

        signerService.delete(id);
    }
}
