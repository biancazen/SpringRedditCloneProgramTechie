package com.example.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "USER_ID")
    private Long userId;
    @NotBlank(message = "Username is required")
    @Column(name = "USERNAME")
    private String username;
    @NotBlank(message = "Password is required")
    @Column(name = "PSW")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CREATED_USER_DATE")
    private Instant created;
    @Column(name = "ENABLED")
    private boolean enabled;
}
