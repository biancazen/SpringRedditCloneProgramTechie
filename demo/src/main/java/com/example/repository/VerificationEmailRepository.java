package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.NotificationEmail;

@Repository
public interface VerificationEmailRepository extends JpaRepository <NotificationEmail, Integer>{

}
