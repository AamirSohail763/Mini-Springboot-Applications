package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EventException;
import com.masai.model.Event;
import com.masai.repository.EventDAO;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventDAO dao;

	@Override
	public Event createEvent(Event event) throws EventException {
		Optional<Event> opt = dao.findById(event.getEventId());
		if(opt.isPresent()) {
			throw new EventException("Event already exists..");
		}
		else {
			return dao.saveAndFlush(event);
		}
	}

	@Override
	public Event updateEvent(Integer eventId,Event event) throws EventException {
		Optional<Event> opt = dao.findById(eventId);
		if(opt.isPresent()) {
			Event eve = opt.get();
			event.setEventId(eve.getEventId());
			return dao.saveAndFlush(event);
		}
		else {
			throw new EventException("Event not found with ID: "+eventId);
		}
	}

	@Override
	public Event deleteEvent(Integer eventId) throws EventException {
		Optional<Event> opt = dao.findById(eventId);
		if(opt.isPresent()) {
			Event event = opt.get();
			dao.delete(event);
			return event;
		}
		else {
			throw new EventException("Event not found with ID: "+eventId);
		}
	}
	
	
	
	

}
