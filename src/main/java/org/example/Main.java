package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;
import org.example.core.mapper.CustomerMapper;
import org.example.core.services.CustomerService;
import org.example.core.services.impl.CustomerServiceImpl;
import org.example.persistence.dao.CustomerDao;
import org.example.persistence.dao.impl.CustomerDaoImpl;
import org.example.persistence.entity.Customer;
import org.example.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
/// entity manager factory is the same as session factory in JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taminPU");

        ///  entity manager  is the same as session  in JPA
        EntityManager em = emf.createEntityManager();


        /// create
        em.getTransaction().begin();
        Customer customer = Customer.builder()
                .firstname("John")
                .lastname("Smith")
                .username("johnsmith3")
                .password("password")
                .phoneNumber("555-555-5577")
                .registerDateTime(LocalDateTime.now())
                .build();
        em.persist(customer);
        em.getTransaction().commit();

//        try {
            CustomerDao customerDao = new CustomerDaoImpl();
            CustomerMapper customerMapper = new CustomerMapper();
            CustomerService customerService = new CustomerServiceImpl(customerDao, customerMapper);
//        Customer customer = Customer.builder()
//                .firstname("John")
//                .lastname("Smith")
//                .username("johnsmith3")
//                .password("password")
//                .phoneNumber("555-555-5577")
//                .registerDateTime(LocalDateTime.now())
//                .build();
            CustomerSaveReqDto reqDto = new CustomerSaveReqDto(
                    "marina", "youkhanva",
                    "marinaaa", "1234", "a@aaa.com", "11111122"
            );

            customerService.save(reqDto);
        System.out.printf("");

//    customerService.update( new CustomerUpdateReqDto(1L, reqDto));
//}finally {
//    HibernateUtil.shutdown();
//}
//
//        }

    }
}