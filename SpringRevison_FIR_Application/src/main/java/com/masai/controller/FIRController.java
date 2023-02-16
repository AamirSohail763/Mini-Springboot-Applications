package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AuthorizationException;
import com.masai.model.FIR;
import com.masai.service.FIRService;
import com.masai.service.UserSessionService;

@RestController
@RequestMapping("/masaifir/user")
public class FIRController {
	
	@Autowired
	FIRService firService;
	
	@Autowired
	UserSessionService session;
	
	
	@PostMapping("/fir")
	public ResponseEntity<FIR> registerDriverHandler(@RequestBody FIR fir, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId!=null) {
			return new ResponseEntity<FIR>(firService.createFIR(fir), HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}

}
