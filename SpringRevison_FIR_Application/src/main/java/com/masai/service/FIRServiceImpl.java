package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FirException;
import com.masai.model.FIR;
import com.masai.repository.FirDAO;

@Service
public class FIRServiceImpl implements FIRService{
	
	@Autowired
	FirDAO firDAO;

	@Override
	public FIR createFIR(FIR fir) throws FirException {
		
		Optional<FIR> opt = firDAO.findById(fir.getFirId());
		if(opt.isPresent()) {
			throw new FirException("FIR already exists..");
		}
		else {
			return firDAO.saveAndFlush(fir);
		}
	}

}
