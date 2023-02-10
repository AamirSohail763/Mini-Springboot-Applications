package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.TagException;
import com.masai.model.Tag;
import com.masai.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	TagService service;
	
	
	@PostMapping("/add")
	public ResponseEntity<Tag> createTagHandler(@RequestBody Tag tag) throws TagException{
		Tag newTag = service.createTag(tag);
		return new ResponseEntity<Tag>(newTag, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Tag> deleteTagHandler(@PathVariable("id") Integer id) throws TagException{
		Tag deletedTag = service.deleteTag(id);
		return new ResponseEntity<Tag>(deletedTag,HttpStatus.OK);
	}

}
