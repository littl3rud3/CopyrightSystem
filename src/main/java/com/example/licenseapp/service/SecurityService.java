package com.example.licenseapp.service;

import com.example.licenseapp.dto.ClientDTO;

public interface SecurityService {
    
    /**
     * Registration.
     *
     * @param userDTO User info
     * @return Created user
     */
    ClientDTO register(ClientDTO userDTO);
    
    /**
     * Change password.
     *
     * @param login Username
     * @param oldPassword old password
     * @param newPassword new password
     */
    void changePassword(String login, String oldPassword, String newPassword);
}
