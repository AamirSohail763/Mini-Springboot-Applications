package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TagException;
import com.masai.model.Tag;
import com.masai.repository.TagDAO;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	TagDAO dao;

	@Override
	public Tag createTag(Tag tag) throws TagException {
		Optional<Tag> opt = dao.findById(tag.getId());
		
		if(opt.isPresent()) {
			throw new TagException("Tag alredy exists..");
		}else {
			return dao.save(tag);
		}
	}

	@Override
	public Tag deleteTag(Integer id) throws TagException {
		Optional<Tag> opt = dao.findById(id);
		if(opt.isPresent()) {
			Tag tag = opt.get();
			dao.delete(tag);
			return tag;
		}else {
			throw new TagException("No tag found with id: "+id);
		}
	}

}
