package com.example.licenseapp.controller;

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

import java.util.List;

@RestController
@RequestMapping("/api/recordings")
@RequiredArgsConstructor
@Tag(name = "Контроллер композиций")
public class RecordingController {

    private final RecordingService recordingService;

    @Operation(summary = "Все композиции")
    @GetMapping
    public List<RecordingDTO> getAll() {

        return recordingService.getAll();
    }

    @Operation(summary = "Композиция по идентификатору")
    @GetMapping("/{id:[\\d]+}")
    public RecordingDTO get(@PathVariable long id) {

        return recordingService.getById(id);
    }

    @Operation(summary = "Добавить композицию")
    @PostMapping
    public void create(@RequestBody RecordingDTO request) {

        recordingService.create(request);
    }

    @Operation(summary = "Удалить композицию по идентификатору")
    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable long id) {

        recordingService.delete(id);
    }

    @Operation(summary = "Удалить исполнителя песни")
    @PutMapping("/removeSinger/{id}")
    public void removeSinger(@PathVariable long id) {

        recordingService.removeSinger(id);
    }

    @Operation(summary = "Редактировать композицию")
    @PutMapping
    public void update(@RequestBody RecordingDTO updatedRecording) {

        recordingService.update(updatedRecording);
    }
}
