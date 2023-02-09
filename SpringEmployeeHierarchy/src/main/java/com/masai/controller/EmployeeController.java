package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl service;
	
	@PostMapping("/add-employee")
	public ResponseEntity<Employee> addEmployeeHandler(@RequestBody Employee employee) throws EmployeeException{
		
		return new ResponseEntity<Employee>(service.addEmployee(employee), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get-employees/{empId}")
	public ResponseEntity<Employee> getEmployeeHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		
		return new ResponseEntity<Employee>(service.getEmployee(empId), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/get-employee-manager/{empId}")
	public ResponseEntity<Employee> getEmployeeManagerHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		
		return new ResponseEntity<Employee>(service.getEmployeeManager(empId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get-all-reportees/{empId}")
	public ResponseEntity<List<Employee>> getAllReporteesHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		
		return new ResponseEntity<List<Employee>>(service.getReportees(empId), HttpStatus.ACCEPTED);
	}

}
