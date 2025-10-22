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
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
