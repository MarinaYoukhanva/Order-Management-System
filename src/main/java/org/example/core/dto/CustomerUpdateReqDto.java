package org.example.core.dto;

public record CustomerUpdateReqDto(
        Long id, CustomerSaveReqDto customerReqDto
) {
}
