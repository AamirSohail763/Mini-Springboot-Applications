package com.masai.authservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.User;
import com.masai.authrepository.UserDAO;
import com.masai.exception.DriverException;
import com.masai.exception.UserException;
import com.masai.model.Driver;
import com.masai.repository.DriverDAO;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDAO signUpDAO;
	
	@Autowired
	private UserSessionService userLoginSession;
	
	@Autowired
	DriverDAO driverdao;
	
	
	@Override
	public User newSignUp(User signUp) throws AuthorizationException {
		
		Optional<User> opt = signUpDAO.findByUserName(signUp.getUserName());
		if(opt.isPresent())
		{
			throw new AuthorizationException("User Already Exists..!!");
		}
		
		return signUpDAO.saveAndFlush(signUp);
	}

	
	@Override
	public User updateSignUp(User signUp, String key) throws AuthorizationException {
		
		User signUpDetails = userLoginSession.getSignUpDetails(key);
		
		if(signUpDetails == null)
		{
			throw new AuthorizationException("User not LoggedIn...!! Try To Login first..");
		}
		
		if(signUpDetails.getUserId() == signUp.getUserId())
			{
			signUpDAO.saveAndFlush(signUp);
			return signUp;
			}
		else
			throw new AuthorizationException("UserId not found..!!");
	}


	@Override
	public List<Driver> getAvailableCabs(Integer userId) {
		List<Driver> foundCabs = new ArrayList<>();
		List<Driver> availableCabs = driverdao.findByCabAvailable(true);
		Optional<User> opt = signUpDAO.findById(userId);
		if(opt.isPresent()) {
			User user = opt.get();
			Integer[] userLocation = user.getCurrentPosition();
			for(Driver d : availableCabs) {
				if( Math.abs(Math.sqrt(Math.pow((userLocation[0]-d.getCurrentLocation()[0]), 2) + Math.pow((userLocation[1]-d.getCurrentLocation()[1]), 2))) <= 5 ) {
					foundCabs.add(d);
				}
			}
			
			if(foundCabs.size() > 0) {
				return foundCabs;
			}
			else {
				throw new DriverException("No Cabs found nearby..");
			}
		}
		else {
			throw new UserException("User not found with ID: "+userId);
		}

	}

	@Override
	public Driver bookRide(Integer driverId, Integer x, Integer y) {
		Optional<Driver> opt = driverdao.findById(driverId);
		if(opt.isPresent()) {
			Driver driver = opt.get();
			Integer[] updatedLocation = new Integer[2];
			updatedLocation[0] = x;
			updatedLocation[1] = y;
			driver.setCurrentLocation(updatedLocation);
			return driverdao.saveAndFlush(driver);
		}
		else {
			throw new DriverException("No Driver found with ID: "+driverId);
		}
	}

}
