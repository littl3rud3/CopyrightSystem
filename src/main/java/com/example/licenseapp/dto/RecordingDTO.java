package com.example.licenseapp.dto;

import com.example.licenseapp.model.Copyright;
import com.example.licenseapp.model.Singer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.List;

@Schema(description = "Песня")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record RecordingDTO(@Schema(description = "Идентификатор песни") Long id,
                           @Schema(description = "Название песни") String title,
                           @Schema(description = "Версия") String version,
                           @Schema(description = "Дата и время релиза песни") ZonedDateTime releaseTime,
                           @Schema(description = "Исполнитель песни") Singer singer,
                           @JsonIgnoreProperties(value = {"recordings"})
                           @Schema(description = "Авторские права") List<Copyright> copyrights) {

}
