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
import com.masai.model.Event;
import com.masai.service.EventService;

@RestController
@RequestMapping("/masaicalender")
public class EventController {
	
	
	@Autowired
	EventService service;
	
	@Autowired
	UserSessionService session;
	
	
	@PostMapping("/event")
	public ResponseEntity<Event> addEventHandler(@RequestBody Event event, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<Event>(service.createEvent(event) , HttpStatus.CREATED);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	
	
	@PutMapping("/event/{eventId}")
	public ResponseEntity<Event> updateEventHandler(@PathVariable("eventId") Integer eventId,@RequestBody Event event, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<Event>(service.updateEvent(eventId, event) , HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}
	
	
	@DeleteMapping("/event/{eventId}")
	public ResponseEntity<Event> deleteEventHandler(@PathVariable("eventId") Integer eventId, @RequestParam String key){
		
		Integer userId = session.getUserSessionId(key);
		if(userId != null) {
			return new ResponseEntity<Event>(service.deleteEvent(eventId) , HttpStatus.OK);
		}
		else {
			throw new AuthorizationException("Please Login to Account first..");
		}
	}

}
