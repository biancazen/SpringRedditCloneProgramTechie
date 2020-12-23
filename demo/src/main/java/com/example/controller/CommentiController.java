package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommentiDto;
import com.example.service.CommentiService;

import static org.springframework.http.ResponseEntity.status;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentiController {

	private final CommentiService commentService;

	@PostMapping
	public ResponseEntity<Void> createComment (@RequestBody CommentiDto commentDto){
		commentService.createComment(commentDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("by-postId/{postId}")
	public ResponseEntity<List<CommentiDto>> getAllCommentsForPost (@RequestParam("postId") Long postId){
		return status(HttpStatus.OK)
				.body(commentService.getCommentByPost(postId));
	}

	@GetMapping("by-user/{userName}")
	public ResponseEntity<List<CommentiDto>> getAllCommentsForUser (@RequestParam("userName") String userName){
		return
				status(HttpStatus.OK).body(commentService.getCommentsByUser(userName));
	}

}
