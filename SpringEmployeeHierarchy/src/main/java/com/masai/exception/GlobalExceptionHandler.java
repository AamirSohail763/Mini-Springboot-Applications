package com.masai.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorDetails> BookExceptionHandler(EmployeeException ee, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(ee.getMessage());
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
