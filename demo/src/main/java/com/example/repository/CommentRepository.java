package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer>{

	List <Comment> findByPost (Post post);
	
	List <Comment> findAllByUser (User user);
}
