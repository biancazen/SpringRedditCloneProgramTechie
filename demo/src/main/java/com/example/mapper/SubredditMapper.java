package com.example.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.SubredditDto;
import com.example.model.Post;
import com.example.model.Subreddit;

/*
 * With this annotation we are specifying that spring should identify this interface as a component
 * and should be able  to inject it into other components like SubredditService
 */
@Mapper(componentModel = "spring")
public interface SubredditMapper {

	@Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditToDto (Subreddit subreddit);
	/*
	 * we are mapping from List<Post> to an Integer, this kind of mapping is not straight
	 * foreward and we need to write our logic
	 */
	default Integer mapPosts(List<Post> postCount) {
		return postCount.size();
	}
	
	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}
