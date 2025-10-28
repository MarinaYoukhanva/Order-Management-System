package org.example.persistence.dao;

import org.example.persistence.entity.OrderItem;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface OrderItemDao {
    OrderItem save(OrderItem orderItem, Session session);

    Optional<OrderItem> findById(Long id, Session session);

    List<OrderItem> findAll(Session session);

    List<OrderItem> findByOrderId(Long orderId, Session session);

    int delete(Long id, Session session);
}
