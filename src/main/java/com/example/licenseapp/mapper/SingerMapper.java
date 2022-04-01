package com.example.licenseapp.mapper;

import com.example.licenseapp.dto.SingerDTO;
import com.example.licenseapp.model.Singer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SingerMapper {
    
    List<SingerDTO> ToDtoList(List<Singer> companyEntities);

    List<Singer> ToEntitiesList(List<SingerDTO> SingerDTOS);

    SingerDTO toDTO(Singer Singer);

    Singer toEntity(SingerDTO SingerDTO);
}
