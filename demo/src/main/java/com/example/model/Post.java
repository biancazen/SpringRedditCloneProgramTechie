package com.example.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "TB_POST")
public class Post {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column(name = "POST_ID")
	private Long postId;
	
	@NotBlank(message = "Post Name cannot be left empty or null")
	@Column(name = "POST_NAME")
	private String postName;
	
	@Nullable
	@Column (name = "URL")
	private String url;
	
	@Nullable
	@Lob	//this is useful because the post description could be very large and difficoult to store, the sideefffect is that it wont be able to be found using sql
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column (name="VOTE_COUNT")
	private Integer voteCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;
	
	@Column ( name = "POST_CREATION_DATE")
	private Instant creationData;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "SUBREDDIT_ID", referencedColumnName = "SUBREDDIT_ID")
	    private Subreddit subreddit;

}
