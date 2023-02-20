package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.MailException;
import com.masai.model.Mail;
import com.masai.repository.MailDAO;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	MailDAO dao;

	@Override
	public Mail addMail(Mail mail) throws MailException {
		Optional<Mail> opt = dao.findById(mail.getMailId());
		if(opt.isPresent()) {
			throw new MailException("Error.. Duplicate mail...");
		}
		else {
			return dao.saveAndFlush(mail);
		}
	}

	@Override
	public Mail starMail(Integer mailId) throws MailException {
		Optional<Mail> opt = dao.findById(mailId);
		if(opt.isPresent()) {
			Mail mail = opt.get();
			mail.setStarred(true);
			return dao.saveAndFlush(mail);
		}
		else {
			throw new MailException("Error.. Mail not found with ID: "+mailId);
		}
	}

	@Override
	public Mail deleteMail(Integer mailId) throws MailException {
		Optional<Mail> opt = dao.findById(mailId);
		if(opt.isPresent()) {
			Mail mail = opt.get();
			dao.delete(mail);
			return mail;
		}
		else {
			throw new MailException("Error.. Mail not found with ID: "+mailId);
		}
	}
	
	
	

}
