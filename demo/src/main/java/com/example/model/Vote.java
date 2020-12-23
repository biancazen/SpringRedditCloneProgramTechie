package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="TB_VOTE")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	@Column(name="VOTE_ID")
	private Long voteId;
	
	private VoteType voteType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;
}
