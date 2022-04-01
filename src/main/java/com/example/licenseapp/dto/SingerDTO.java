package com.example.licenseapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Исполнитель песни")
public record SingerDTO(@Schema(description = "Идентификатор песни") Long id,
                        @Schema(description = "Имя исполнителя") String name) {

}
