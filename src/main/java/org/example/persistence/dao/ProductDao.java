package org.example.persistence.dao;

import org.example.persistence.entity.Product;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(Product product, Session session);

    Optional<Product> findById(Long id, Session session);

    List<Product> findAll(Session session);

    int delete(Long id, Session session);
}
