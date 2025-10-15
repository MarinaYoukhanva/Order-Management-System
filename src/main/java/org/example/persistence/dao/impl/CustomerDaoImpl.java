package org.example.persistence.dao.impl;

import org.example.persistence.dao.CustomerDao;
import org.example.persistence.entity.Customer;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer save(Customer customer) {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            session.persist(customer) ;
            return customer;
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            return session.byId(Customer.class).loadOptional(id) ;
        }
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            return session.createQuery("from Customer c where c.username = :username", Customer.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        }
    }

    @Override
    public List<Customer> findAll() {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            return session.createQuery("from Customer", Customer.class)
                    .list();
        }
    }

    @Override
    public Customer update(Customer customer) {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            session.persist(customer);
            return customer;
        }
    }

    @Override
    public int delete(Long id) {
        try(Session session = HibernateUtil.sessionFactory.openSession()){
            return session.createMutationQuery("delete from Customer c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }
    }
}
