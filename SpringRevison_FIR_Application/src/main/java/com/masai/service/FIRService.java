package com.masai.service;

import com.masai.exception.FirException;
import com.masai.model.FIR;

public interface FIRService {
	
	public FIR createFIR(FIR fir) throws FirException;

}
