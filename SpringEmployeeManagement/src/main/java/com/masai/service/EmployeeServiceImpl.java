package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.repository.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDAO dao;

	@Override
	public Employee registerEmployee(Employee emp) throws EmployeeException {
		
		Employee savedEmployee = dao.save(emp);
		
		if(savedEmployee !=null) {
			return savedEmployee;
		}else {
			throw new EmployeeException("Invaild details entered...");
		}
		
	}

	@Override
	public Employee getEmployeeById(Integer empId) throws EmployeeException {
		Optional<Employee> opt = dao.findById(empId);
		
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new EmployeeException("No Employee found with ID: "+empId);
		}
	}

	
	
	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		List<Employee> employees = dao.findAll();
		
		if(employees.size() > 0) {
			return employees;
		}else {
			throw new EmployeeException("No Employee exists..");
		}
	}

	@Override
	public Employee deleteEmployeeById(Integer empId) throws EmployeeException {
		
		Optional<Employee> opt = dao.findById(empId);
		
		if(opt.isPresent()) {
			Employee emp = opt.get();
			dao.delete(emp);
			
			return emp;
		}else {
			throw new EmployeeException("Employee doesn't exist with ID: "+empId);
		}
		
	}

	
	@Override
	public List<Employee> getEmployeeDetailsByAddress(String address) throws EmployeeException {
		
		List<Employee> employees = dao.findByAddress(address);
		
		if(employees.size() > 0) {
			return employees;
		}else {
			throw new EmployeeException("No Employee found with Address: "+address);
		}
	}

	@Override
	public Employee updateEmployee(Employee emp) throws EmployeeException {
		Optional<Employee> opt = dao.findById(emp.getEmpId());
		
		if(opt.isPresent()) {
			return dao.save(emp);
			
		}else {
			throw new EmployeeException("Invalid Employee details..");
		}
	}

	@Override
	public String[] getNameAndAddressOfEmplyeeById(Integer empId) throws EmployeeException {
		String result = dao.getNameAndAddress(empId);
		
		if(result!=null) {
			String[] sr = result.split(",");
			return sr;
		}else {
			throw new EmployeeException("Employee doesn't exist with ID: "+empId);
		}
		
		
	}

	@Override
	public List<EmployeeDTO> getNameAddressSalaryOfAllEmployee() throws EmployeeException {
		
		List<EmployeeDTO> emps = dao.getNameAddressSalaryOfAllEmployee();
		
		if(emps.size() > 0) {
			return emps;
		}else {
			throw new EmployeeException("No Employees found..");
		}
		
		
	}

}
