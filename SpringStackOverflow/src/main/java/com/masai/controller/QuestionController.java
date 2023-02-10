package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.QuestionException;
import com.masai.exception.TagException;
import com.masai.model.Question;
import com.masai.model.Tag;
import com.masai.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<Question> createQuestionHandler(@RequestBody Question question) throws QuestionException{
		Question newQuestion = service.createQuestion(question);
		return new ResponseEntity<Question>(newQuestion,HttpStatus.CREATED);
	}
	
	
	@PatchMapping("/edit/{id}")
	public ResponseEntity<Question> updateQuestionHandler(@RequestBody Question question,@PathVariable("id") Integer id) throws QuestionException{
		Question updatedQuestion = service.updateQuestion(id, question);
		return new ResponseEntity<Question>(updatedQuestion,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Question> deleteQuestionHandler(@PathVariable("id") Integer id) throws QuestionException{
		Question deletedQuestion = service.deleteQuestion(id);
		return new ResponseEntity<Question>(deletedQuestion,HttpStatus.OK);
	}
	
	
	@PostMapping("/add-tags/{id}")
	public ResponseEntity<Question> addTagToQuestionHandler(@RequestBody Tag tag,@PathVariable("id") Integer id) throws QuestionException, TagException{
		Question taggedQuestion = service.addTagToQuestion(id, tag);
		return new ResponseEntity<Question>(taggedQuestion,HttpStatus.ACCEPTED);
	}

}
