package com.example.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.PostRequest;
import com.example.dto.PostResponse;
import com.example.exception.DemoException;
import com.example.mapper.PostMapper;
import com.example.model.Post;
import com.example.model.Subreddit;
import com.example.model.User;
import com.example.repository.PostRepository;
import com.example.repository.SubredditRepository;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

	private final PostRepository postRepository;
	private final SubredditRepository subredditRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final PostMapper postMapper;
	
	@Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new DemoException(postId.toString()));
        return postMapper.mapToDto(post);
    }
	
	@Transactional(readOnly = true)
	public List<PostResponse> getAllPosts(){
		return postRepository.findAll()
				.stream().map(postMapper::mapToDto)
				.collect(toList());
	}
	
	public void save (PostRequest postRequest) {
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
				.orElseThrow(() -> new DemoException ("Subreddit not found with name" + postRequest.getSubredditName()));
		postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getPostsBySubreddit (Long subredditId) {
		Subreddit subreddit = subredditRepository.findById(subredditId)
				.orElseThrow(() -> new DemoException(subredditId.toString()));
		List<Post> posts = postRepository.findAllBySubreddit(subreddit);
		return
			posts.stream().map(postMapper::mapToDto).collect(toList());
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getPostsByUsername (String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> 
		new DemoException("post from username -" + username + "not found"));
		return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(toList());
	}
}
