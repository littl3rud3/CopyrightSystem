package com.example.licenseapp.dto;

import com.example.licenseapp.model.Copyright;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Компания")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record CompanyDTO(@Schema(description = "Идентификатор компании") Long id,
                         @Schema(description = "Название компании") String name,
                         @JsonIgnoreProperties(value = {"company"})
                         @Schema(description = "Авторские права") List<Copyright> copyrights) {

}
