package org.example.core.services.impl;

import org.example.core.services.OrderService;
import org.example.persistence.dao.CustomerDao;
import org.example.persistence.dao.OrderDao;
import org.example.persistence.dao.OrderItemDao;
import org.example.persistence.dao.ProductDao;
import org.example.persistence.entity.Customer;
import org.example.persistence.entity.Order;
import org.example.persistence.entity.OrderItem;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.enums.OrderStatus;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private final OrderItemDao orderItemDao;
    private final ProductDao productDao;

    public OrderServiceImpl(
            OrderDao orderDao, CustomerDao customerDao, OrderItemDao orderItemDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.orderItemDao = orderItemDao;
        this.productDao = productDao;
    }

    @Override
    public Order placeOrder(Long customerId, List<OrderItem> orderItems) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            try {
                Customer customer = customerDao.findById(customerId, session)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
                Order order = Order.builder()
                        .orderDate(LocalDateTime.now())
                        .status(OrderStatus.NEW)
                        .customer(customer)
                        .build();

                order = orderDao.save(order, session);
                for (OrderItem orderItem : orderItems) {
                    orderItem.setOrder(order);
//                    todo: fetch all products at once or pass ids or placeOrderDto to method
                    Product product = productDao.findById(
                            orderItem.getProduct().getId(), session).get();
                    product.setStock(
                            product.getStock() - orderItem.getQuantity());
                    productDao.save(product, session);
                    orderItemDao.save(orderItem, session);
                }
                session.getTransaction().commit();
                return order;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Order> findAllOrdersByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return orderDao.findAllByCustomerId(customerId, session);
        }
    }
}
