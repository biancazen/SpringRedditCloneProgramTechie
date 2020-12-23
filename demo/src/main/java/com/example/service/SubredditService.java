package com.example.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.SubredditDto;
import com.example.exception.DemoException;
import com.example.mapper.SubredditMapper;
import com.example.model.Subreddit;
import com.example.repository.SubredditRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditService {

	private final SubredditRepository subredditRepository;
	private final AuthService authService;
	private final SubredditMapper subredditMapper;

	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll().stream()
				.map(subredditMapper::mapSubredditToDto).collect(toList());
	}

	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		subredditDto.setSubredditId(save.getSubredditId());
		return subredditDto;
	}

	@Transactional(readOnly = true)
	public SubredditDto getSubreddit(Long id) {
		Subreddit subreddit = subredditRepository.findById(id)
				.orElseThrow(() -> new DemoException("Subreddit not found with id -" + id));
		return subredditMapper.mapSubredditToDto(subreddit);
	}
}