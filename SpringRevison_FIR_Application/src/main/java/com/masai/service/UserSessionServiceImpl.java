package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AuthorizationException;
import com.masai.model.User;
import com.masai.model.UserSession;
import com.masai.repository.UserDAO;
import com.masai.repository.UserSessionDAO;

@Service
public class UserSessionServiceImpl implements UserSessionService {

	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private UserDAO signUpDAO;
	
	
	
	@Override
	public UserSession getUserSession(String key) throws AuthorizationException {
		
		Optional<UserSession> currentUser = userSessionDAO.findByUUID(key);
		if(!currentUser.isPresent())
		{
			throw new AuthorizationException("Not Authorized..!!");
		}
		return currentUser.get();
	}

	@Override
	public Integer getUserSessionId(String key) throws AuthorizationException {
		
		Optional<UserSession> currentUser = userSessionDAO.findByUUID(key);
		if(!currentUser.isPresent())
		{
			throw new AuthorizationException("Not Authorized..!!");
		}
		return currentUser.get().getId();
		
	}


	@Override
	public User getSignUpDetails(String key) {
		
		Optional<UserSession> currentUser = userSessionDAO.findByUUID(key);
		if(!currentUser.isPresent())
		{
			return null;
		}
		Integer SignUpUserId = currentUser.get().getUserId();
		System.out.println(SignUpUserId );
		
		return (signUpDAO.findById(SignUpUserId)).get();
	}

}
