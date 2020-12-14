package com.example.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class DemoApplication {

	 public static final SessionFactory sessionFactory = buildSessionFactory(); 
	 
	 @Bean(name ="entityManagerFactory")
	    private static SessionFactory buildSessionFactory() { //Create the SessionFactory from hibernate.cfg.xml
	        try {
	            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();  //Create the SessionFactory from hibernate.cfg.xml
	        } catch (Throwable ex) {
	            System.out.println("Problem creating session factory");
	            ex.printStackTrace();
	            return null;
	        }
	    }
	 

	public static void main(String[] args) {
        System.setProperty("server.port", "8082");
		SpringApplication.run(DemoApplication.class, args);		
		
		
	}

}
