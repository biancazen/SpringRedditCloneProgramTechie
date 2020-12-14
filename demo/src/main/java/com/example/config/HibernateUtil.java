package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory(); //

    private static SessionFactory buildSessionFactory() { //Create the SessionFactory from hibernate.cfg.xml
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();  //Create the SessionFactory from hibernate.cfg.xml
        } catch (Throwable ex) {
            System.out.println("Problem creating session factory");
            ex.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {// Return an istance of session.
        return sessionFactory;
    }

    public static void shutdown() {// Close caches and connection pools
        getSessionFactory().close();
    }

}