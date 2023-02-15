package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.User;
import com.masai.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO dao;

	@Override
	public User addUser(User user) throws UserException {
		Optional<User> usr = dao.findByEmail(user.getEmail());
		if(usr.isPresent()) {
			throw new UserException("User already exists");
		}
		else {
			return dao.saveAndFlush(user);
		}
		
	}

	@Override
	public User updateUser(User user) throws UserException {
		Optional<User> opt = dao.findByEmail(user.getEmail());
		if(opt.isPresent()) {
			return dao.saveAndFlush(user);
		}
		else {
			throw new UserException("User not found with email: "+user.getEmail());
		}
	}

//	@Override
//	public List<Event> getEventList(String email,String type) {
//		Optional<User> opt =  dao.findByEmail(email);
//		if(opt.isPresent()) {
//			User user = opt.get();
//			List<Event> events = new ArrayList<>();
//			List<Event> userEvent = user.getEvents();
//			for(Event e : userEvent) {
//				if(e.getStartAt().getMonth().equals(type)) {
//					
//				}
//			}
//		}
//	}

	
	
	
	

}
