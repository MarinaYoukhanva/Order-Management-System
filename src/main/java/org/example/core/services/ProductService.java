package org.example.core.services;

import org.example.persistence.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}
