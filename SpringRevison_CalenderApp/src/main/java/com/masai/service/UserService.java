package com.masai.service;

import java.util.List;

import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.User;

public interface UserService {
	
	public User addUser(User user) throws UserException;
	
	public User updateUser(User user) throws UserException;
	
//	public List<Event> getEventList(String email,String type);
	

}
