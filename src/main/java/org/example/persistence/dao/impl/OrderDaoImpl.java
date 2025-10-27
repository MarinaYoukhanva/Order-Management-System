package org.example.persistence.dao.impl;

import org.example.persistence.dao.OrderDao;
import org.example.persistence.entity.Order;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order save(Order order, Session session) {
        session.persist(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id, Session session) {
        return session.byId(Order.class).loadOptional(id);
    }

    @Override
    public List<Order> findAll(Session session) {
        return session.createQuery("from Order", Order.class)
                .list();
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId, Session session) {
        return session.createQuery("from Order o where o.customer.id = :customerId", Order.class)
                .setParameter("customerId", 1L)
                .list();
    }

    @Override
    public int delete(Long id, Session session) {
        return session.createMutationQuery("delete from Order o where o.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
