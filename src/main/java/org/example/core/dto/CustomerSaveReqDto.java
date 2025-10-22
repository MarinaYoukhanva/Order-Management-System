package org.example.core.dto;

public record CustomerSaveReqDto(

        String firstname,
        String lastname,
        String username,
        String password,
        String email,
        String phoneNumber
) {
}
