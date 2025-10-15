package org.example.persistence.dao;

import org.example.persistence.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByUsername(String username);

    List<Customer> findAll();

    Customer update(Customer customer);

    int delete(Long id);
}
