package com.example.licenseapp.service;

import com.example.licenseapp.dto.ClientDTO;

public interface SecurityService {

    ClientDTO register(ClientDTO userDTO);

    void changePassword(String login, String oldPassword, String newPassword);
}
