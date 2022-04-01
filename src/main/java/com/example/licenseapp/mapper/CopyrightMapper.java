package com.example.licenseapp.mapper;

import com.example.licenseapp.dto.CopyrightDTO;
import com.example.licenseapp.model.Copyright;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CopyrightMapper {

    List<CopyrightDTO> ToDtoList(List<Copyright> companyEntities);

    List<Copyright> ToEntitiesList(List<CopyrightDTO> CopyrightDTOS);

    CopyrightDTO toDTO(Copyright companyEntity);

    Copyright toEntity(CopyrightDTO CopyrightDTO);
}
