package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Driver;
import com.masai.service.DriverService;

@RestController
@RequestMapping("/masaicab/driver")
public class DriverController {
	
	@Autowired
	DriverService driverService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriverHandler(@RequestBody Driver driver){
		
			return new ResponseEntity<Driver>(driverService.registerDriver(driver) , HttpStatus.CREATED);
		
	}

}
