package com.masai.service;

import com.masai.exception.TagException;
import com.masai.model.Tag;

public interface TagService {
	
	public Tag createTag(Tag tag)throws TagException;
	
	public Tag deleteTag(Integer id) throws TagException;

}
