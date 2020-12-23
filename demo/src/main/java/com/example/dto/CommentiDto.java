package com.example.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentiDto {

	private Long commentId;
	private Long postId;
	private Instant createdDate;
	private String text;
	private String userName;
}
