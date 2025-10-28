package org.example.core.services.impl;

import org.example.core.services.OrderItemService;
import org.example.persistence.dao.OrderItemDao;
import org.example.persistence.entity.OrderItem;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDao orderItemDao;

    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return orderItemDao.findByOrderId(orderId, session);
        }
    }
}
