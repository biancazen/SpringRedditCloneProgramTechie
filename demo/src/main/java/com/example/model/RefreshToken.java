package com.example.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_REFRESHTOKEN")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REFRESHTOKEN_ID")
    private Long id;
    @NotEmpty
	@Column(name = "TOKEN")
    private String token;
    @NotEmpty
   	@Column(name = "CREATED_DATE")
    private Instant createdDate;
}