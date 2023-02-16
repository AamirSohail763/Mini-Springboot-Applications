package com.masai.service;

import com.masai.exception.AuthorizationException;
import com.masai.model.LogInModel;

public interface LogInService {
	
	public String LogIn(LogInModel login) throws AuthorizationException;
	
	public String LogOut(String key) throws AuthorizationException;

}
