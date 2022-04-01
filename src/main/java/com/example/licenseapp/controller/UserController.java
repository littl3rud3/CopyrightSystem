package com.example.licenseapp.controller;

import com.example.licenseapp.dto.ClientDTO;
import com.example.licenseapp.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с пользователем")
@RequestMapping("/api/users")
public class UserController {

    private final SecurityService securityService;

    @PostMapping
    @Operation(description = "Регистрация пользователя")
    public ClientDTO register(ClientDTO userDTO) {

        return securityService.register(userDTO);
    }

    @GetMapping("/change-password")
    @Operation(description = "Смена пароля пользователя")
    /** Корректнее передавать пароль в POST запросе */
    public void changePassword(String login, String oldPassword, String newPassword) {

        securityService.changePassword(login, oldPassword, newPassword);
    }
}
