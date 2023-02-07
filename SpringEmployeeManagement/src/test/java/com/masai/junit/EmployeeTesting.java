package com.masai.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.service.EmployeeService;
import com.masai.service.EmployeeServiceImpl;

public class EmployeeTesting {
	
	static EmployeeService empService = null;
	
	@BeforeAll
	public static  void beforeAll() {
		empService = new EmployeeServiceImpl();
	}
	
	
	
	@Test
	public void addEmployeeTest() throws EmployeeException {
		assertInstanceOf(Employee.class, empService.registerEmployee(new Employee(101, "Ram", 7500, "Delhi", "ram@mail.com", "984320087")));
	}
	
	@Test
	public void addEmployeeFailTest() throws EmployeeException {
		assertNull(empService.registerEmployee(null));
	}
	
	@Test
	public void deleteEmployeeTest() throws EmployeeException {
		assertEquals(true, empService.deleteEmployeeById(1001));
	}
	
	@Test
	public void deleteEmployeeWithExceptionTest() {
		assertThrows(EmployeeException.class, ()-> empService.deleteEmployeeById(1010));
	}
	
	

}
