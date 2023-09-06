package com.example.licenseapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.licenseapp.model.Company;
import com.example.licenseapp.model.Recording;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Copyright")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record CopyrightDTO(@Schema(description = "ID") Long id,
                           @Schema(description = "Name") String title,
                           @Schema(description = "Expiration date") LocalDate expireDt,
                           @Schema(description = "Cost") Long cost,
                           @JsonIgnoreProperties(value = {"copyrights"})
                           @Schema(description = "The company that owns the copyright") Company company,
                           @JsonIgnoreProperties(value = {"copyrights"})
                           @Schema(description = "Songs with this copyright") List<Recording> recordings) {
    
}
