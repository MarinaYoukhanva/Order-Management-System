package org.example.core.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.core.dto.CustomerResDto;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;
import org.example.core.mapper.CustomerMapper;
import org.example.core.services.CustomerService;
import org.example.persistence.dao.CustomerDao;
import org.example.persistence.entity.Customer;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao;
    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerDao customerDao,
                               CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResDto save(CustomerSaveReqDto reqDto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            try {
                CustomerResDto res = customerMapper.toDto(
                        customerDao.save(
                                customerMapper.toEntity(reqDto), session)
                );
                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public CustomerResDto update(CustomerUpdateReqDto reqDto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Customer customer = customerDao.findById(reqDto.id(), session)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            try {
                CustomerResDto res = customerMapper.toDto(
                        customerDao.save(customer, session)
                );
                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public CustomerResDto findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return customerDao.findById(id, session)
                    .map(customer -> customerMapper.toDto(customer))
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        }
    }

    @Override
    public CustomerResDto findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return customerDao.findByUsername(username, session)
                    .map(customer -> customerMapper.toDto(customer))
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        }
    }

    @Override
    public List<CustomerResDto> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return customerDao.findAll(session)
                    .stream().map(customer -> customerMapper.toDto(customer))
                    .toList();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            try {
                int affectedRows = customerDao.delete(id, session);
                if (affectedRows == 0)
                    throw new EntityNotFoundException("Customer not found");
                session.getTransaction().commit();
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
