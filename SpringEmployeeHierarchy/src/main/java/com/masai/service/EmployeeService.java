package com.masai.service;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;

public interface EmployeeService {
	
	public Employee addEmployee(Employee employee) throws EmployeeException;
	
	public Employee getEmployee(Integer empId)throws EmployeeException;
	
	public Employee getEmployeeManager(Integer empId)throws EmployeeException;

}
