package com.masai.authservice;

import java.util.List;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.User;
import com.masai.exception.UserException;
import com.masai.model.Driver;

public interface UserService {
	
	public User newSignUp(User signUp) throws AuthorizationException;
	
	public User updateSignUp(User signUp, String key) throws AuthorizationException;
	
	public List<Driver> getAvailableCabs(Integer userId) throws UserException;
	
	public Driver bookRide(Integer driverId, Integer x, Integer y);

}
