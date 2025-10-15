package org.example.util;

import org.example.persistence.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
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

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory closed");
        }
    }

    static {
        // Register shutdown hook for pure Java apps
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM exiting, closing SessionFactory...");
            shutdown();
        }));
    }

}
