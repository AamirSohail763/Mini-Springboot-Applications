package com.masai.authservice;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.LogInModel;

public interface LogInModelService {
	
	public String LogIn(LogInModel login) throws AuthorizationException;
	
	public String LogOut(String key) throws AuthorizationException;

}
