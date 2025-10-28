package org.example.persistence.dao.impl;

import org.example.persistence.dao.OrderItemDao;
import org.example.persistence.entity.OrderItem;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public OrderItem save(OrderItem orderItem, Session session) {
        session.persist(orderItem);
        return orderItem;
    }

    @Override
    public Optional<OrderItem> findById(Long id, Session session) {
        return session.byId(OrderItem.class).loadOptional(id);
    }

    @Override
    public List<OrderItem> findAll(Session session) {
        return session.createQuery("from OrderItem", OrderItem.class)
                .list();
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId, Session session) {
        return session.createQuery(
                        "from OrderItem o where o.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .list();
    }

    @Override
    public int delete(Long id, Session session) {
        return session.createMutationQuery("delete from OrderItem oi where oi.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
