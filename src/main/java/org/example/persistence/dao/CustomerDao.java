package org.example.persistence.dao;

import org.example.persistence.entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    Customer save(Customer customer, Session session);

    Optional<Customer> findById(Long id, Session session);

    Optional<Customer> findByUsername(String username, Session session);

    List<Customer> findAll(Session session);

    int delete(Long id, Session session);
}
