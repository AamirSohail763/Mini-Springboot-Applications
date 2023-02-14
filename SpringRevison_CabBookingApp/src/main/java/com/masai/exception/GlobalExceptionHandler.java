package com.masai.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.masai.authexceptions.AuthorizationException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> CommentExceptionHandler(UserException ue, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(ue.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<ErrorDetails> PostExceptionHandler(DriverException de, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(de.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<ErrorDetails> AuthorizationExceptionHandler(AuthorizationException ae, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(ae.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(e.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

}
