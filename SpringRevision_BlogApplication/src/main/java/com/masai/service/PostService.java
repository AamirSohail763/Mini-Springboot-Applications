package com.masai.service;

import java.util.List;

import com.masai.model.Post;

public interface PostService {
	
	public Post createPost(Post post);
	
	public Post updatePost(Integer id, Post post);
	
	public Post deletePost(Integer id);
	
	public Post getPostById(Integer id);
	
	public List<Post> getAllPosts();

}
