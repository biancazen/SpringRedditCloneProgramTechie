package com.example.service;

import static com.example.model.VoteType.UPVOTE;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.VoteDto;
import com.example.exception.DemoException;
import com.example.model.Post;
import com.example.model.Vote;
import com.example.repository.PostRepository;
import com.example.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
	
	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService;
	
	@Transactional
	public void vote (VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getPostId())
				.orElseThrow(() -> new DemoException("Post not found with ID -" + voteDto.getPostId()));
		Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
		  if (voteByPostAndUser.isPresent() &&
	                voteByPostAndUser.get().getVoteType()
	                        .equals(voteDto.getVoteType())) {
	            throw new DemoException("You have already "
	                    + voteDto.getVoteType() + "'d for this post");
	        }
		  if(UPVOTE.equals(voteDto.getVoteType())) {
			  post.setVoteCount(post.getVoteCount() +1);
		  } else {
	            post.setVoteCount(post.getVoteCount() - 1);
	        }
	voteRepository.save(mapToVote(voteDto, post));
	postRepository.save(post);
	}
	
	private Vote mapToVote(VoteDto voteDto, Post post) {
		return Vote.builder().voteType(voteDto.getVoteType())
				.post(post)
				.user(authService.getCurrentUser())
				.build();
	}

}
