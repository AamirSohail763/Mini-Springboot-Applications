package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AuthorizationException;
import com.masai.model.FIR;
import com.masai.model.User;
import com.masai.service.UserService;
import com.masai.service.UserSessionService;

@RestController
@RequestMapping("/masaifir/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserSessionService session;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> createNewAccountHandler(@RequestBody User newSignUp) throws AuthorizationException {
		
		User newSignedUp = userService.newSignUp(newSignUp);
		return new ResponseEntity<User>(newSignedUp,HttpStatus.CREATED);

	}
	
	
	
	@GetMapping("/fir")
	public ResponseEntity<List<FIR>> getAllFIRsHandler(@RequestParam Integer userId, @RequestParam String key){
		
		Integer userid = session.getUserSessionId(key);
		if(userid!=null){
			
			return new ResponseEntity<List<FIR>>(userService.getAllFIR(userId), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	

	
	@DeleteMapping("/fir/{firId}")
	public ResponseEntity<FIR> withdrawFIRHandler(@PathVariable("firId") Integer firId, @RequestParam Integer userId ,@RequestParam String key){
		
		Integer userid = session.getUserSessionId(key);
		if(userid!=null){
			
			return new ResponseEntity<FIR>(userService.withdrawFIR(userId, firId), HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}

}
