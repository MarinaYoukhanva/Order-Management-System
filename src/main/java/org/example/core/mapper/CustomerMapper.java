package org.example.core.mapper;

import org.example.core.dto.CustomerResDto;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.persistence.entity.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {

    public Customer toEntity(CustomerSaveReqDto reqDto) {
        return Customer.builder()
                .firstname(reqDto.firstname())
                .lastname(reqDto.lastname())
                .username(reqDto.username())
                .password(reqDto.password())
                .phoneNumber(reqDto.phoneNumber())
                .email(reqDto.email())
                .registerDateTime(LocalDateTime.now())
                .build();
    }

    public CustomerResDto toDto(Customer customer) {
        return new CustomerResDto(
                customer.getFirstname(),
                customer.getLastname(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getRegisterDateTime()
        );
    }
}
