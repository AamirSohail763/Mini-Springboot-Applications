package com.masai.service;

import java.util.List;

import com.masai.exception.AuthorizationException;
import com.masai.model.FIR;
import com.masai.model.User;

public interface UserService {
	
	public User newSignUp(User signUp) throws AuthorizationException;
	
	public List<FIR> getAllFIR(Integer userId);
	
	public FIR withdrawFIR(Integer userId, Integer firId);

}
