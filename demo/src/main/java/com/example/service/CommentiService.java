package com.example.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.CommentiDto;
import com.example.exception.DemoException;
import com.example.mapper.CommentiMapper;
import com.example.model.Comment;
import com.example.model.NotificationEmail;
import com.example.model.Post;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentiService {

	//TODO: Construct POST URL
	private static final String POST_URL = "";

	private final CommentiMapper commentiMapper;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;

	public void createComment(CommentiDto commentDto) {
		Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new DemoException("post not found with id -" + commentDto.getPostId().toString()));
		Comment comment = commentiMapper.map(commentDto, post, authService.getCurrentUser());
		commentRepository.save(comment);

		String message = mailContentBuilder.build(post.getUser().getUsername() + "posted a comment on your post." + POST_URL);
		sendCommentNotification(message, post.getUser());
	}
	
	  public List<CommentiDto> getCommentByPost(Long postId) {
	        Post post = postRepository.findById(postId)
	                .orElseThrow(() -> new DemoException("post note found with id- " + postId.toString()));
	        return commentRepository.findByPost(post)
	                .stream()
	                .map(commentiMapper::mapToDto)
	                .collect(toList());
	    }

	public List<CommentiDto> getCommentsByUser(String userName) {
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new DemoException("user not found with username -" + userName));
		return commentRepository.findAllByUser(user).stream().map(commentiMapper::mapToDto).collect(toList());
	}

	private void sendCommentNotification (String message, User user) {
		mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
	}

}
