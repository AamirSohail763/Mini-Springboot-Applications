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
import com.masai.model.Comment;
import com.masai.service.CommentService;
import com.masai.service.PostService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserSessionService session;
	
	
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<Comment> addCommentHandler(@PathVariable("postId") Integer postId ,@RequestBody Comment comment,@RequestParam String key){

		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<Comment>(commentService.addComment(postId, comment), HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("User not found..");
		}
	}
	
	
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<Comment> updateCommentHandler(@PathVariable("postId") Integer postId,@PathVariable("id") Integer id,@RequestBody Comment comment){

		return new ResponseEntity<Comment>(commentService.updateComment(postId, id, comment), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<Comment> deleteCommentHandler(@PathVariable("postId") Integer postId,@PathVariable("id") Integer id,@RequestParam String key){

		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<Comment>(commentService.deleteComment(postId, id), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("User not found..");
		}	
	}
	
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<Comment> getCommentHandler(@PathVariable("postId") Integer postId,@PathVariable("id") Integer id){
		
		return new ResponseEntity<Comment>(commentService.getCommentById(postId, id), HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<Comment>> getAllCommentsHandler(@PathVariable("postId") Integer postId){
		
		return new ResponseEntity<List<Comment>>(commentService.getAllComments(postId), HttpStatus.OK);
	}
}
