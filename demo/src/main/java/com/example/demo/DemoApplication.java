package com.example.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.example.controller", "com.example.service", "com.example.config", "com.example.security", "com.example.model", "com.example.mapper"})
@EnableJpaRepositories("com.example.repository")
@EnableAsync
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
