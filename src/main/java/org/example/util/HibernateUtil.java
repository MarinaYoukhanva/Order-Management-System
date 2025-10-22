package org.example.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.persistence.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create JPA EntityManagerFactory from persistence.xml
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("orders") ;

                // Unwrap the Hibernate SessionFactory
                return emf.unwrap(SessionFactory.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create SessionFactory from persistence.xml", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory closed.");
        }
    }

//    @Getter
//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        EntityManagerFactory emf = Persistence
//                .createEntityManagerFactory("order-management-system");
//            return emf.unwrap(SessionFactory.class);
//
//    }

//    public static void shutdown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//            System.out.println("SessionFactory closed");
//        }
//    }
//
//    static {
//        // Register shutdown hook for pure Java apps
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("JVM exiting, closing SessionFactory...");
//            shutdown();
//        }));
//    }

}
