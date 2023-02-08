package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDAO dao;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Optional<Customer> opt = dao.findById(customer.getCustomerId());
		if(opt.isPresent()) {
			throw new CustomerException("Customer already exists");
		}else {
			return dao.save(customer);
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Optional<Customer> opt = dao.findById(customer.getCustomerId());
		if(opt.isPresent()) {
			return dao.save(customer);
		}else {
			throw new CustomerException("No such Customer found..");
		}
	}

	@Override
	public Customer removeCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> opt = dao.findById(customerId);
		if(opt.isPresent()) {
			Customer customer = opt.get();
			dao.delete(customer);
			return customer;
		}else {
			throw new CustomerException("No Customer found with ID: "+customerId);
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> opt = dao.findById(customerId);
		if(opt.isPresent()) {
			Customer customer = opt.get();
			return customer;
		}else {
			throw new CustomerException("No Customer found with ID: "+customerId);
		}
	}
	
	

}
