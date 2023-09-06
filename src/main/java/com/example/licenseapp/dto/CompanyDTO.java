package com.example.licenseapp.dto;

import java.util.List;

import com.example.licenseapp.model.Copyright;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record CompanyDTO(@Schema(description = "ID") Long id,
                         @Schema(description = "name") String name,
                         @JsonIgnoreProperties(value = {"company"})
                         @Schema(description = "Copyrights") List<Copyright> copyrights) {
    
}
