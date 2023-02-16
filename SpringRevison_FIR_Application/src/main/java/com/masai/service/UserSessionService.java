package com.masai.service;

import com.masai.exception.AuthorizationException;
import com.masai.model.User;
import com.masai.model.UserSession;

public interface UserSessionService {
	
	public UserSession getUserSession(String key) throws AuthorizationException;
	
	public Integer getUserSessionId(String key) throws AuthorizationException;
	
	public User getSignUpDetails(String key) throws AuthorizationException;
	

}
