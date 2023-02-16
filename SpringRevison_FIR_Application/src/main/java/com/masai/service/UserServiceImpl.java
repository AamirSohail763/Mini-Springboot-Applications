package com.masai.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AuthorizationException;
import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;
import com.masai.model.User;
import com.masai.repository.FirDAO;
import com.masai.repository.UserDAO;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	FirDAO firDAO;
	
	@Override
	public User newSignUp(User user) throws AuthorizationException {
		
		Optional<User> opt = userDAO.findByUserName(user.getUserName());
		if(opt.isPresent())
		{
			throw new AuthorizationException("User Already exists..");
		}
		
		return userDAO.saveAndFlush(user);
	}



	@Override
	public List<FIR> getAllFIR(Integer userId) {	
		Optional<User> opt = userDAO.findById(userId);
		if(opt.isPresent()) {
			User user = opt.get();
			List<FIR> FIRs = user.getFIRs();
			if(FIRs.size() > 0) {
				return FIRs;
			}
			else {
				throw new FirException("No FIRs found...");
			}
		}
		else {
			throw new UserException("User not found with ID: "+userId);
		}
	}


	@Override
	public FIR withdrawFIR(Integer userId, Integer firId) {
		
		Optional<User> opt = userDAO.findById(userId);
		if(opt.isPresent()) {
			User user = opt.get();
			List<FIR> FIRs = user.getFIRs();
			Iterator<FIR> firs = FIRs.iterator();
			while(firs.hasNext()) {
				FIR f = firs.next();
				if(f.getFirId()==firId) {
					if(f.getTimeStamp().isBefore(LocalDateTime.now())) {
						firs.remove();
						user.setFIRs(FIRs);
						userDAO.saveAndFlush(user);
						firDAO.delete(f);
						return f;
					}
					else {
						throw new FirException("Reporting time not within 24-hours..");
					}
				}
			}
			throw new FirException("FIR not found with ID: "+firId);
		}
		else {
			throw new UserException("User not found with ID: "+userId);
		}
	}




}
