package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masai.exception.PostException;
import com.masai.model.Post;
import com.masai.repository.PostDAO;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostDAO dao;
	

	@Override
	public Post createPost(Post post) {
		Optional<Post> opt = dao.findById(post.getId());
		if(opt.isPresent()) {
			throw new PostException("Post already exists..");
		}
		else {
			return dao.saveAndFlush(post);
		}
	}

	
	@Override
	@Transactional
	public Post updatePost(Integer id, Post post) {
		Optional<Post> opt = dao.findById(id);
		if(opt.isPresent()) {
			return dao.saveAndFlush(post);
		}
		else {
			throw new PostException("No Post found with ID: "+id);
		}
	}

	
	@Override
	public Post deletePost(Integer id) {
		Optional<Post> opt = dao.findById(id);
		if(opt.isPresent()) {
			Post post = opt.get();
			dao.delete(post);
			return post;
		}
		else {
			throw new PostException("No Post found with ID: "+id);
		}
	}

	
	@Override
	public Post getPostById(Integer id) {
		Optional<Post> opt = dao.findById(id);
		if(opt.isPresent()) {
			Post post = opt.get();
			return post;
		}
		else {
			throw new PostException("No Post found with ID: "+id);
		}
	}

	
	@Override
	public List<Post> getAllPosts() {
		List<Post> posts = dao.findAll();
		return posts;
	}

}
