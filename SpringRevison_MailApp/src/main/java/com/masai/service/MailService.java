package com.masai.service;

import com.masai.exception.MailException;
import com.masai.model.Mail;

public interface MailService {
	
	public Mail addMail(Mail mail) throws MailException;
	
	public Mail starMail(Integer mailId) throws MailException;
	
	public Mail deleteMail(Integer mailId) throws MailException;	

}
