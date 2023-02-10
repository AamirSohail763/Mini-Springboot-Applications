package com.masai.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {
	
	private LocalDate timestamp;
	private String message;
	private String details;
	
	


}
