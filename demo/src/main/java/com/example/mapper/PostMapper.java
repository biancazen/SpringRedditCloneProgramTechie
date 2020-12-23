package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.PostRequest;
import com.example.dto.PostResponse;
import com.example.model.Post;
import com.example.model.Subreddit;
import com.example.model.User;
import com.example.repository.CommentRepository;	


@Mapper(componentModel = "spring")
public abstract class PostMapper {
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Mapping(target = "voteCount", constant = "0")
	@Mapping(target = "creationData", expression = "java(java.time.Instant.now())")
	@Mapping(target = "user", source ="user")
	@Mapping(target = "subreddit", source ="subreddit")
	@Mapping(target = "description", source ="postRequest.description")
	public abstract Post map (PostRequest postRequest, Subreddit subreddit, User user);
	
	@Mapping(target = "postId", source = "postId")
	@Mapping(target = "postName", source = "postName")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "url", source = "url")
	@Mapping(target = "subredditName", source = "subreddit.name")
	@Mapping(target = "userName", source = "user.username")
	@Mapping(target = "commentCount", expression = "java(commentCount(post))")
	public abstract PostResponse mapToDto (Post post);
	
	Integer commentCount(Post post) {
		return commentRepository.findByPost(post).size();
	}
	
}
