package org.example;

import org.example.persistence.dao.CustomerDao;
import org.example.persistence.dao.impl.CustomerDaoImpl;
import org.example.persistence.entity.Customer;
import org.example.util.HibernateUtil;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

    CustomerDao customerDao = new CustomerDaoImpl();

        Customer customer = Customer.builder()
                .firstname("John")
                .lastname("Smith")
                .username("johnsmith")
                .password("password")
                .phoneNumber("555-555-5555")
                .registerDateTime(LocalDateTime.now())
                .build();


        customerDao.save(customer);
    }

}