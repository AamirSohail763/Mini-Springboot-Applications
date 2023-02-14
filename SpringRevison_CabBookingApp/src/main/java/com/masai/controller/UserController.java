package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.User;
import com.masai.authservice.UserService;
import com.masai.authservice.UserSessionService;
import com.masai.model.Driver;

@RestController
@RequestMapping("/masaicab/user")
public class UserController {
	
	@Autowired
	private UserService signUpService;
	
	@Autowired
	UserSessionService session;
	
	@PostMapping("/register")
	public ResponseEntity<User> createNewAccountHandler(@RequestBody User newSignUp) throws AuthorizationException {
		
		User newSignedUp =signUpService.newSignUp(newSignUp);
		return new ResponseEntity<User>(newSignedUp,HttpStatus.CREATED);

	}
	
	
	@PutMapping("/updateAccount")
	public ResponseEntity<User> updateAccountDetailsHandler(@RequestBody User signUp, @RequestParam String key) throws AuthorizationException
	{
		User newUpdatedSignUp = signUpService.updateSignUp(signUp,key);
		
		return new ResponseEntity<User>(newUpdatedSignUp,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/findride")
	public ResponseEntity<List<Driver>> getAvailableCabsHandler(@RequestParam Integer userId , @RequestParam String key){
		
		Integer userid = session.getUserSessionId(key);
		if(userid != null) {
			return new ResponseEntity<List<Driver>>(signUpService.getAvailableCabs(userId) ,HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	
	
	@PutMapping("/book/{driverId}/{x}/{y}")
	public ResponseEntity<Driver> bookRideHandler(@PathVariable("driverId") Integer driverId, @PathVariable("x") Integer x, @PathVariable("y") Integer y, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<Driver>(signUpService.bookRide(driverId, x, y),HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}

}
