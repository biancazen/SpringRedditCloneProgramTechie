package com.example.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SUBREDDIT")
public class Subreddit {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column(name = "SUBREDDIT_ID")	
	private Long id;
	
	@Nullable
	@Column (name = "SUB_DESCRIPTION")
	private String description;
	
	@NotBlank(message = "Post Name cannot be left empty or null")
	@Column(name = "SUBREDDIT_NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private Long userId;
	
	@Column ( name = "POST_CREATION_DATE")
	private Instant creationData;

}
