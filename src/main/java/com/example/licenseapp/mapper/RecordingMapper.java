package com.example.licenseapp.mapper;

import com.example.licenseapp.dto.RecordingDTO;
import com.example.licenseapp.model.Recording;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RecordingMapper {

    List<RecordingDTO> ToDtoList(List<Recording> companyEntities);

    List<Recording> ToEntitiesList(List<RecordingDTO> RecordingDTOS);

    RecordingDTO toDTO(Recording Recording);

    Recording toEntity(RecordingDTO RecordingDTO);
}
