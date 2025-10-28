package org.example.core.services;

import org.example.persistence.entity.Order;
import org.example.persistence.entity.OrderItem;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long customerId, List<OrderItem> orderItems);

    List<Order> findAllOrdersByCustomerId(Long customerId);
}
