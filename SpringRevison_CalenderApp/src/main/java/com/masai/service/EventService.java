package com.masai.service;

import com.masai.exception.EventException;
import com.masai.model.Event;

public interface EventService {
	
	public Event createEvent(Event event) throws EventException;
	
	public Event updateEvent(Integer eventId,Event event) throws EventException;
	
	public Event deleteEvent(Integer eventId) throws EventException;

}
