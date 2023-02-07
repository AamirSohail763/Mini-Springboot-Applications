package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	
	
	
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> registerEmployeeHandler(@RequestBody Employee emp) throws EmployeeException{
		
		Employee savedEmployee = employeeService.registerEmployee(emp);
		
		return new ResponseEntity<Employee>(savedEmployee,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmployeeByIdHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		Employee employee = employeeService.getEmployeeById(empId);
		
		return new ResponseEntity<Employee>(employee,HttpStatus.FOUND);
	}
	
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesHandler() throws EmployeeException{
		List<Employee> employees = employeeService.getAllEmployeeDetails();
		
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.FOUND);
	}
	
	
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<Employee> deleteEmployeeByIdHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		Employee removedEmployee = employeeService.deleteEmployeeById(empId);
		
		return new ResponseEntity<Employee>(removedEmployee,HttpStatus.OK);
	}
	
	
	@GetMapping("/getemployees/{address}")
	public ResponseEntity<List<Employee>> getEmployeesByAddressHandler(@PathVariable("address") String address) throws EmployeeException{
		List<Employee> employees = employeeService.getEmployeeDetailsByAddress(address);
		
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.FOUND);
	}
	
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployeeHandler(@RequestBody Employee emp) throws EmployeeException{
		Employee updatedEmp = employeeService.updateEmployee(emp);
		
		return new ResponseEntity<Employee>(updatedEmp,HttpStatus.OK);
	}
	
	
	@GetMapping("/getnameandaddress/{empId}")
	public ResponseEntity<String[]> getEmployeeNameAndAddressHandler(@PathVariable("empId") Integer empId) throws EmployeeException{
		String[] result = employeeService.getNameAndAddressOfEmplyeeById(empId);
		
		return new ResponseEntity<String[]>(result,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/employeesdto")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeeNameAddressSalary() throws EmployeeException{
		List<EmployeeDTO> empsDTO = employeeService.getNameAddressSalaryOfAllEmployee();
		
		return new ResponseEntity<List<EmployeeDTO>>(empsDTO,HttpStatus.OK);
	}

}
