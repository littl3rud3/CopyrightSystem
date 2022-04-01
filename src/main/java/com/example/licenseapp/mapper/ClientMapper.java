package com.example.licenseapp.mapper;

import com.example.licenseapp.dto.ClientDTO;
import com.example.licenseapp.model.security.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientDTO toDTO(Client user);

    Client toEntity(ClientDTO userDTO);
}
