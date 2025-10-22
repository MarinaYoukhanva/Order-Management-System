package org.example.core.dto;

import java.time.LocalDateTime;

public record CustomerResDto(

        String firstname,
        String lastname,
        String username,
        String email,
        String phoneNumber,
        LocalDateTime registerDateTime
) {
}
