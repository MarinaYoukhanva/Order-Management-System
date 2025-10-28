package org.example.core.services;

import org.example.persistence.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllByOrderId(Long orderId);
}
