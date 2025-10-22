package org.example;

import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;
import org.example.core.mapper.CustomerMapper;
import org.example.core.services.CustomerService;
import org.example.core.services.impl.CustomerServiceImpl;
import org.example.persistence.dao.CustomerDao;
import org.example.persistence.dao.impl.CustomerDaoImpl;
import org.example.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
//try {
//    CustomerDao customerDao = new CustomerDaoImpl();
//    CustomerMapper customerMapper = new CustomerMapper();
//    CustomerService customerService = new CustomerServiceImpl(customerDao, customerMapper);
////        Customer customer = Customer.builder()
////                .firstname("John")
////                .lastname("Smith")
////                .username("johnsmith3")
////                .password("password")
////                .phoneNumber("555-555-5577")
////                .registerDateTime(LocalDateTime.now())
////                .build();
//    CustomerSaveReqDto reqDto = new CustomerSaveReqDto(
//            "marina","youkhanva",
//            "marinaa", "1234", "a@aa.com", "111111"
//    );
//
////        customerService.save(reqDto);
//
//    customerService.update( new CustomerUpdateReqDto(1L, reqDto));
//}finally {
    HibernateUtil.shutdown();
//}
//
    }


}