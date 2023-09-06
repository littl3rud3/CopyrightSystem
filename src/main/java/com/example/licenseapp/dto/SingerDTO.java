package com.example.licenseapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Artist")
public record SingerDTO(@Schema(description = "ID") Long id,
                        @Schema(description = "Name") String name) {
    
}
