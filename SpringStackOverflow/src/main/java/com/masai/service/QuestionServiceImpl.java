package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.QuestionException;
import com.masai.exception.TagException;
import com.masai.model.Question;
import com.masai.model.Tag;
import com.masai.repository.QuestionDAO;
import com.masai.repository.TagDAO;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionDAO Qdao;
	
	@Autowired
	TagDAO Tdao;

	@Override
	public Question createQuestion(Question question) throws QuestionException {
		Optional<Question> opt = Qdao.findById(question.getId());
		if(opt.isPresent()) {
			throw new QuestionException("Question already present..");
		}else {
			return Qdao.save(question);
		}
	}

	@Override
	public Question updateQuestion(Integer id, Question question) throws QuestionException {
		Optional<Question> opt = Qdao.findById(id);
		if(opt.isPresent()) {
			return Qdao.save(question);
		}else {
			throw new QuestionException("Question not found with id: "+id);
		}
	}

	@Override
	public Question deleteQuestion(Integer id) throws QuestionException {
		Optional<Question> opt = Qdao.findById(id);
		if(opt.isPresent()) {
			Question question = opt.get();
			Qdao.delete(question);
			return question;
		}else {
			throw new QuestionException("Question not found with id: "+id);
		}
	}

	@Override
	public Question addTagToQuestion(Integer id, Tag tag) throws QuestionException, TagException {
		Optional<Question> Qopt = Qdao.findById(id);
		if(Qopt.isPresent()) {
			Question question = Qopt.get();
			Optional<Tag> Topt = Tdao.findById(tag.getId());
			if(Topt.isPresent()) {
				Tag t = Topt.get();
				List<Tag> taglist = question.getTaglist();
				taglist.add(t);
				return Qdao.save(question);
			}else {
				throw new TagException("Invalid tag..Please Create Tag first..");
			}
		}else{
			throw new QuestionException("Question not found with id: "+id);
		}
	}
	
	

}
