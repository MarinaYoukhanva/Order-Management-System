package org.example.core.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.core.services.ProductService;
import org.example.persistence.dao.ProductDao;
import org.example.persistence.entity.Product;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            try {
                Product res = productDao.save(product, session);
                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Product update(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Product productToUpdate = productDao.findById(product.getId(), session)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            try {
                Product res = productDao.save(product, session);
                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return productDao.findById(id, session)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return productDao.findAll(session);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            try {
                int affectedRows = productDao.delete(id, session);
                if (affectedRows == 0)
                    throw new EntityNotFoundException("Product not found");
                session.getTransaction().commit();
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
