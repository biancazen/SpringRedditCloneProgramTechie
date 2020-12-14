package com.example.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TOKEN")
public class VerificationToken {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name ="TOKEN_ID")
	private Integer id;

	@Column(name = "TOKEN")
	private String token;
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;
	
	@Column (name = "EXPIRATION_DATE")
	private Instant expiryDate;


}
