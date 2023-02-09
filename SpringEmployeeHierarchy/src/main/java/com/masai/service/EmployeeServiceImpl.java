package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.repository.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDAO dao;

	@Override
	public Employee getEmployee(Integer empId) throws EmployeeException {
		Optional<Employee> opt = dao.findById(empId);
		if(opt.isPresent()) {
			Employee employee = opt.get();
			return employee;
			
		}else {
			throw new EmployeeException("No Employee found with ID: "+empId);
		}
	}

	@Override
	public Employee getEmployeeManager(Integer empId) throws EmployeeException {
		Optional<Employee> opt = dao.findById(empId);
		if(opt.isPresent()) {
			Employee employee = opt.get();
			Integer managerId = employee.getManagerId();
			if(managerId!= 0) {
				Optional<Employee> Mopt = dao.findById(managerId);
				return Mopt.get();
				
			}else {
				throw new EmployeeException("Given Employee doesn't have reporting Manager");
			}
			
		}else {
			throw new EmployeeException("No Employee found with ID: "+empId);
		}
	}
	
	public List<Employee> getReportees(Integer empId)throws EmployeeException {
		List<Employee> reporteelist =  dao.getAllReportees(empId);
		if(reporteelist.size() > 0) {
			return reporteelist;
		}else {
			throw new EmployeeException("No Reportees found for given Employee");
		}
	}

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeException {
		Optional<Employee> opt = dao.findById(employee.getEmpId());
		if(opt.isPresent()) {
			throw new EmployeeException("Employee already exists..");
		}
		else {
			return dao.saveAndFlush(employee);
		}
	}

}
