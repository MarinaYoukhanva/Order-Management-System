package org.example.core.services;

import org.example.core.dto.CustomerResDto;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;

public interface CustomerService {
    CustomerResDto save (CustomerSaveReqDto reqDto);

    CustomerResDto update(CustomerUpdateReqDto reqDto);
}
