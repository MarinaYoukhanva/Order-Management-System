package org.example.core.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CustomerSaveReqDto(

        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @NotBlank
        String username,
        @NotBlank
        String password,
        String email,
        @NotBlank
        String phoneNumber
) {
}
