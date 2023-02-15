package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authservice.UserSessionService;
import com.masai.model.User;
import com.masai.service.UserService;


@RestController
@RequestMapping("/masaicalender")
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserSessionService session;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> addUserHandler(@RequestBody User user, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<User>(service.addUser(user), HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	
	
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUserHandler(@RequestBody User user, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<User>(service.updateUser(user), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	
	
	
	

}
