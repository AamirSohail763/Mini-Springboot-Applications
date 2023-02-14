package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.DriverException;
import com.masai.model.Driver;
import com.masai.repository.DriverDAO;

@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	DriverDAO driverdao;

	@Override
	public Driver registerDriver(Driver driver) throws DriverException {
		Optional<Driver> opt = driverdao.findById(driver.getDriverId());
		if(opt.isPresent()) {
			throw new DriverException("Driver alredy registered..");
		}
		else {
			return driverdao.saveAndFlush(driver);
		}
	}

}
