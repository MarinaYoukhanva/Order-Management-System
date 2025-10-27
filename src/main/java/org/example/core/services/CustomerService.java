package org.example.core.services;

import org.example.core.dto.CustomerResDto;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;

import java.util.List;

public interface CustomerService {
    CustomerResDto save (CustomerSaveReqDto reqDto);

    CustomerResDto update(CustomerUpdateReqDto reqDto);

    CustomerResDto findById(Long id);

    CustomerResDto findByUsername(String username);

    List<CustomerResDto> findAll();

    void deleteById(Long id);
}
