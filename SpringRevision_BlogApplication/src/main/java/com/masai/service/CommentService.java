package com.masai.service;

import java.util.List;

import com.masai.model.Comment;

public interface CommentService {
	
	public Comment addComment(Integer postId, Comment comment);
	
	public Comment updateComment(Integer postId, Integer id, Comment comment);
	
	public Comment deleteComment(Integer postId, Integer id);
	
	public List<Comment> getAllComments(Integer postId);
	
	public Comment getCommentById(Integer postId, Integer id);

}
