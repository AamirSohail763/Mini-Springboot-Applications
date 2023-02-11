package com.masai.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.UserSession;
import com.masai.authservice.UserSessionService;
import com.masai.model.Post;
import com.masai.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserSessionService session;
	
	
	
	@PostMapping("/posts")
	public ResponseEntity<Post> addPostHandler(@RequestBody Post post,@RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("User not found..");
		}
	}

				
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePostHandler(@PathVariable("id") Integer id,@RequestBody Post post,@RequestParam String key){

		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<Post>(postService.updatePost(id, post), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("User not found..");
		}	
	}
	
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Post> deletePostHandler(@PathVariable("id") Integer id,@RequestParam String key){

		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<Post>(postService.deletePost(id), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("User not found..");
		}
	}
	
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPostHandler(@PathVariable("id") Integer id){
		
		return new ResponseEntity<Post>(postService.getPostById(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPostsHandler(){
		
		return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
	}

}
