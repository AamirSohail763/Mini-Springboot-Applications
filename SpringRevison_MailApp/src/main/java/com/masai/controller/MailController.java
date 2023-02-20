package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authservice.UserSessionService;
import com.masai.model.Mail;
import com.masai.service.MailService;

@RestController
@RequestMapping("/masaimail")
public class MailController {
	
	@Autowired
	MailService service;
	
	@Autowired
	UserSessionService session;
	
	
	@PostMapping("/mail")
	public ResponseEntity<Mail> addMailHandler(@RequestBody Mail mail, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<Mail>(service.addMail(mail), HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("User not found..");
		}
	}
	
	
	@PutMapping("/starred/{mailId}")
	public ResponseEntity<Mail> starMailHandler(@PathVariable("mailId") Integer mailId , @RequestParam String key){
			
			Integer userId = session.getUserSessionId(key);
			if(userId != null) {
				return new ResponseEntity<Mail>(service.starMail(mailId), HttpStatus.OK);
			}
			else {
				throw new AuthorizationException("User not found..");
			}
		}
	
	
	@DeleteMapping("/delete/{mailId}")
	public ResponseEntity<Mail> deleteMailHandler(@PathVariable("mailId") Integer mailId , @RequestParam String key){
			
			Integer userId = session.getUserSessionId(key);
			if(userId != null) {
				return new ResponseEntity<Mail>(service.deleteMail(mailId), HttpStatus.OK);
			}
			else {
				throw new AuthorizationException("User not found..");
			}
		}

}
