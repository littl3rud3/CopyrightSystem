package com.example.licenseapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь")
public record ClientDTO(@Schema(description = "Идентификатор пользователя") Long userId,
                        @Schema(description = "Логин пользователя") String login,
                        @JsonIgnore
                        @Schema(description = "Пароль пользователя")
                        String password) {

}
