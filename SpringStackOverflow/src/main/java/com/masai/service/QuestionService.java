package com.masai.service;

import com.masai.exception.QuestionException;
import com.masai.exception.TagException;
import com.masai.model.Question;
import com.masai.model.Tag;

public interface QuestionService {
	
	public Question createQuestion(Question question) throws QuestionException;
	
	public Question updateQuestion(Integer id, Question question) throws QuestionException;
	
	public Question deleteQuestion(Integer id) throws QuestionException;
	
	public Question addTagToQuestion(Integer id,Tag tag) throws QuestionException, TagException;

}
