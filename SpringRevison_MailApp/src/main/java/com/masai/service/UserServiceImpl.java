package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.MailException;
import com.masai.exception.UserException;
import com.masai.model.Mail;
import com.masai.model.User;
import com.masai.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO dao;

	@Override
	public User addUser(User user) throws UserException {
		Optional<User> usr = dao.findByEmail(user.getEmail());
		if(usr.isPresent()) {
			throw new UserException("User already exists");
		}
		else {
			return dao.saveAndFlush(user);
		}
		
	}

	@Override
	public User updateUser(User user) throws UserException {
		Optional<User> opt = dao.findByEmail(user.getEmail());
		if(opt.isPresent()) {
			return dao.saveAndFlush(user);
		}
		else {
			throw new UserException("User not found with email: "+user.getEmail());
		}
	}

	@Override
	public List<Mail> getAllMails(String email) throws UserException {
		Optional<User> opt = dao.findByEmail(email);
		if(opt.isPresent()) {
			User usr = opt.get();
			List<Mail> mails = usr.getMails();
			if(mails.size()>0) {
				return mails;
			}
			else {
				throw new MailException("Error.. No mails found..");
			}
		}
		else {
			throw new UserException("User not found with email: "+email); 
		}
	}

	@Override
	public List<Mail> getAllStarredMails(String email) throws UserException {
		Optional<User> opt = dao.findByEmail(email);
		if(opt.isPresent()) {
			User usr = opt.get();
			List<Mail> mails = usr.getMails();
			List<Mail> starredMails = new ArrayList<>();
			for(Mail m : mails) {
				if(m.getStarred()==true) {
					starredMails.add(m);
				}
			}
			
			if(starredMails.size()>0) {
				return starredMails;
			}
			else {
				throw new MailException("Error.. No starred mails found...");
			}
		}
		else {
			throw new UserException("User not found with email: "+email); 
		}
	}
	
	
	

}
