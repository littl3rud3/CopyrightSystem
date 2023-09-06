package com.example.licenseapp.controller;

import com.example.licenseapp.dto.ClientDTO;
import com.example.licenseapp.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User controller")
@RequestMapping("/api/users")
public class UserController {
    
    private final SecurityService securityService;
    
    @PostMapping
    @Operation(description = "Register user")
    public ClientDTO register(ClientDTO userDTO) {
        
        return securityService.register(userDTO);
    }
    
    @PatchMapping("/change-password")
    @Operation(description = "Change password")
    public void changePassword(String login, String oldPassword, String newPassword) {
        
        securityService.changePassword(login, oldPassword, newPassword);
    }
}
