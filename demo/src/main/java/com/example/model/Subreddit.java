package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="TB_SUBREDDIT")
public class Subreddit {
	
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "SUBREDDIT_ID")
    private Long subredditId;
    
    @NotBlank(message = "Community name is required")
    @Column(name = "NAME")
    private String name;
    
    @NotBlank(message = "Description is required")
    @Column(name = "DESCRIPTION")
    private String description;
    
    @OneToMany(fetch = LAZY)
    @Column( name = "POST_ID")
    private List<Post> posts;
    
    @Column(name = "CREATED_TIME")
    private Instant createdDate;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn( name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
}