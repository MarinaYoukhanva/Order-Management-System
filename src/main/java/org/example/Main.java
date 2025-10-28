package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.core.dto.CustomerSaveReqDto;
import org.example.core.dto.CustomerUpdateReqDto;
import org.example.core.mapper.CustomerMapper;
import org.example.core.services.CustomerService;
import org.example.core.services.OrderItemService;
import org.example.core.services.OrderService;
import org.example.core.services.ProductService;
import org.example.core.services.impl.CustomerServiceImpl;
import org.example.core.services.impl.OrderItemServiceImpl;
import org.example.core.services.impl.OrderServiceImpl;
import org.example.core.services.impl.ProductServiceImpl;
import org.example.persistence.dao.CustomerDao;
import org.example.persistence.dao.OrderDao;
import org.example.persistence.dao.OrderItemDao;
import org.example.persistence.dao.ProductDao;
import org.example.persistence.dao.impl.CustomerDaoImpl;
import org.example.persistence.dao.impl.OrderDaoImpl;
import org.example.persistence.dao.impl.OrderItemDaoImpl;
import org.example.persistence.dao.impl.ProductDaoImpl;
import org.example.persistence.entity.Customer;
import org.example.persistence.entity.Order;
import org.example.persistence.entity.OrderItem;
import org.example.persistence.entity.Product;
import org.example.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerService customerService = new CustomerServiceImpl(customerDao, customerMapper);
        ProductService productService = new ProductServiceImpl(productDao);
        OrderItemService orderItemService = new OrderItemServiceImpl(orderItemDao);
        OrderService orderService = new OrderServiceImpl(orderDao, customerDao, orderItemDao, productDao);

//        Product product1 = Product.builder()
//                .name("coat1")
//                .price(100D)
//                .stock(20)
//                .build();
//
//        Product product2 = Product.builder()
//                .name("coat2")
//                .price(200D)
//                .stock(15)
//                .build();
//
//        productService.save(product1);
//        productService.save(product2);
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(
//                OrderItem.builder()
//                        .product(product1)
//                        .quantity(3)
//                        .unitPrice(120D)
//                        .build());
//        orderItems.add(
//                OrderItem.builder()
//                        .product(product2)
//                        .quantity(4)
//                        .unitPrice(210D)
//                        .build());
//        orderService.placeOrder(2l, orderItems);

//        todo: better query
        List<Order> orders = orderService.findAllOrdersByCustomerId(2L);
        List<List<OrderItem>> orderItems = orders.stream()
                .map(order -> orderItemService.findAllByOrderId(order.getId()))
                .toList();
        System.out.println(orders);
        System.out.println(orderItems);


///// entity manager factory is the same as session factory in JPA
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taminPU");
//
//        ///  entity manager  is the same as session  in JPA
//        EntityManager em = emf.createEntityManager();
//
//
//        /// create
//        em.getTransaction().begin();
//        Customer customer = Customer.builder()
//                .firstname("John")
//                .lastname("Smith")
//                .username("johnsmith3")
//                .password("password")
//                .phoneNumber("555-555-5577")
//                .registerDateTime(LocalDateTime.now())
//                .build();
//        em.persist(customer);
//        em.getTransaction().commit();
//
////        try {
//
////        Customer customer = Customer.builder()
////                .firstname("John")
////                .lastname("Smith")
////                .username("johnsmith3")
////                .password("password")
////                .phoneNumber("555-555-5577")
////                .registerDateTime(LocalDateTime.now())
////                .build();
//        CustomerSaveReqDto reqDto = new CustomerSaveReqDto(
//                "marina", "youkhanva",
//                "marinaaa", "1234", "a@aaa.com", "11111122"
//        );
//
//        customerService.save(reqDto);
//        System.out.printf("");
//
////    customerService.update( new CustomerUpdateReqDto(1L, reqDto));
////}finally {
////    HibernateUtil.shutdown();
////}
////
////        }

    }
}