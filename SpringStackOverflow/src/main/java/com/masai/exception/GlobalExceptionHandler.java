package com.masai.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(TagException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(TagException te, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(te.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(QuestionException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(QuestionException qe, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(qe.getMessage());
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
