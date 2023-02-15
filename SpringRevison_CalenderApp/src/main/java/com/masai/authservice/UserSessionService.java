package com.masai.authservice;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.SignUpModel;
import com.masai.authmodels.UserSession;

public interface UserSessionService {
	
	public UserSession getUserSession(String key) throws AuthorizationException;
	
	public Integer getUserSessionId(String key) throws AuthorizationException;
	
	public SignUpModel getSignUpDetails(String key) throws AuthorizationException;
	

}
