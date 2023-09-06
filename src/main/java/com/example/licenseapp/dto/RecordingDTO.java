package com.example.licenseapp.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.example.licenseapp.model.Copyright;
import com.example.licenseapp.model.Singer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Song")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record RecordingDTO(@Schema(description = "ID") Long id,
                           @Schema(description = "Title") String title,
                           @Schema(description = "Version") String version,
                           @Schema(description = "Date and time of the song's release") ZonedDateTime releaseTime,
                           @Schema(description = "The artist of the song") Singer singer,
                           @JsonIgnoreProperties(value = {"recordings"})
                           @Schema(description = "Copyrights") List<Copyright> copyrights) {
    
}
