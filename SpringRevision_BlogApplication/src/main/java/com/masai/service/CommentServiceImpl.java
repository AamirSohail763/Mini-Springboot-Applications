package com.masai.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CommentException;
import com.masai.exception.PostException;
import com.masai.model.Comment;
import com.masai.model.Post;
import com.masai.repository.CommentDAO;
import com.masai.repository.PostDAO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDAO dao;
	
	@Autowired
	PostDAO postDAO;

	@Override
	@Transactional
	public Comment addComment(Integer postId, Comment comment) {
		Optional<Post> pOpt = postDAO.findById(postId);
		if(pOpt.isPresent()) {
			Post post = pOpt.get();
			Optional<Comment> cOpt = dao.findById(comment.getId());
			if(cOpt.isPresent()) {
				throw new CommentException("This Comment already exists..");
			}
			else {
				Comment comm = dao.saveAndFlush(comment);
				List<Comment> commentList = post.getComments();
				commentList.add(comm);
				post.setComments(commentList);
				return comm;
			}
		}
		else {
			throw new PostException("Post not found with ID: "+postId);
		}
	}

	
	
	@Override
	public Comment updateComment(Integer postId, Integer id, Comment comment) {
		Optional<Post> pOpt = postDAO.findById(postId);
		if(pOpt.isPresent()) {
			Post post = pOpt.get();
			Optional<Comment> cOpt = dao.findById(id);
			if(cOpt.isPresent()) {
				Comment comm = dao.saveAndFlush(comment);
				post.getComments().add(comm);
				return comm;
			}
			else {
				throw new CommentException("No Comment found with ID: "+id);
			}
		}
		else {
			throw new PostException("Post not found with ID: "+postId);
		}
	}



	@Override
	public Comment deleteComment(Integer postId, Integer id) {
		Optional<Post> pOpt = postDAO.findById(postId);
		if(pOpt.isPresent()) {
			Post post = pOpt.get();
			Optional<Comment> cOpt = dao.findById(id);
			if(cOpt.isPresent()) {
				Comment comm = cOpt.get();
				dao.delete(comm);
				List<Comment> list = post.getComments();
				list.remove(comm);
				post.setComments(list);
				return comm;
			}else {
				throw new CommentException("No Comment found with ID: "+id);
			}
		}else {
			throw new PostException("Post not found with ID: "+postId);
		}
	}



	@Override
	public List<Comment> getAllComments(Integer postId) {
		Optional<Post> opt = postDAO.findById(postId);
		if(opt.isPresent()) {
			Post post = opt.get();
			List<Comment> comments = post.getComments();
			if(comments.size() != 0) {
				return comments;
			}
			else {
				throw new CommentException("No comments found on the Post");
			}
		}
		else {
			throw new PostException("Post not found with ID: "+postId);
		}
	}



	@Override
	public Comment getCommentById(Integer postId, Integer id) {
		Optional<Post> pOpt = postDAO.findById(postId);
		if(pOpt.isPresent()) {
			Optional<Comment> cOpt = dao.findById(id);
			if(cOpt.isPresent()) {
				Comment comment = cOpt.get();
				return comment;
			}else {
				throw new CommentException("No Comment found with ID: "+id);
			}
		}
		else {
			throw new PostException("Post not found with ID: "+postId);
		}
	}
	
	
	
	
	

}
