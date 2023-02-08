package com.masai.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(CustomerException ce, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(ce.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(ProductException pe, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(pe.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(OrderException oe, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(oe.getMessage());
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
