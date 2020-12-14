package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Like;
import com.example.model.Post;
import com.example.model.User;

@Repository
public interface LikeRepository extends JpaRepository <Like, Integer>{

	Optional <Like> findTopPostAndUserOrderByVoteIdDesc (Post post, User currentUser);
}
