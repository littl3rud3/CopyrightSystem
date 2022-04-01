package com.example.licenseapp.mapper;

import com.example.licenseapp.dto.CompanyDTO;
import com.example.licenseapp.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<CompanyDTO> ToDtoList(List<Company> companyEntities);

    List<Company> ToEntitiesList(List<CompanyDTO> companyDTOS);

    CompanyDTO toDTO(Company companyEntity);

    Company toEntity(CompanyDTO companyDTO);
}
