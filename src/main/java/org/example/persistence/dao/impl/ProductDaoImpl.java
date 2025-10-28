package org.example.persistence.dao.impl;

import org.example.persistence.dao.ProductDao;
import org.example.persistence.entity.Product;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product save(Product product, Session session) {
        session.persist(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id, Session session) {
        return session.byId(Product.class).loadOptional(id);
    }

    @Override
    public List<Product> findAll(Session session) {
        return session.createQuery("from Product", Product.class)
                .list();
    }

    @Override
    public int delete(Long id, Session session) {
        return session.createMutationQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
