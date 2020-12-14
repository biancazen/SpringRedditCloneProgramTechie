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
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_COMMENT")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COMMENT_ID")
	private Integer commentId;
	
	@NotEmpty
	@Column(name = "TEXT")
	private String text;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID")
	private Post post;
	
	@Column (name = "COMMENT_CREATION_DATE")
	private Instant createdDate;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;


}
