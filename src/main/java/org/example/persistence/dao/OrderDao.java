package org.example.persistence.dao;

import org.example.persistence.entity.Order;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order save(Order order, Session session);

    Optional<Order> findById(Long id, Session session);

    List<Order> findAll(Session session);

    List<Order> findAllByCustomerId(Long customerId, Session session);

    int delete(Long id, Session session);
}
