package com.example.licenseapp.dto;

import com.example.licenseapp.model.Company;
import com.example.licenseapp.model.Recording;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Авторское право")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record CopyrightDTO(@Schema(description = "Идентификатор") Long id,
                           @Schema(description = "Название") String title,
                           @Schema(description = "Дата истечения срока") LocalDate expireDt,
                           @Schema(description = "Стоимость") Long cost,
                           @JsonIgnoreProperties(value = {"copyrights"})
                           @Schema(description = "Компания, владеющая авторским правом") Company company,
                           @JsonIgnoreProperties(value = {"copyrights"})
                           @Schema(description = "Песни с данным авторским правом") List<Recording> recordings)  {

}
