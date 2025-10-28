package org.example.persistence.dao.impl;

import org.example.persistence.dao.CustomerDao;
import org.example.persistence.entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer save(Customer customer, Session session) {
        session.persist(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id, Session session) {
        return session.byId(Customer.class).loadOptional(id);
    }

    @Override
    public Optional<Customer> findByUsername(String username, Session session) {
        return session.createQuery("from Customer c where c.username = :username", Customer.class)
                .setParameter("username", username)
                .uniqueResultOptional();
    }

    @Override
    public List<Customer> findAll(Session session) {
        return session.createQuery("from Customer", Customer.class)
                .list();
    }

    @Override
    public int delete(Long id, Session session) {
        return session.createMutationQuery("delete from Customer c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
