package com.masai.authservice;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.SignUpModel;

public interface SignUpModelService {
	
	public SignUpModel newSignUp(SignUpModel signUp) throws AuthorizationException;;
	
	public SignUpModel updateSignUp(SignUpModel signUp, String key) throws AuthorizationException;;

}
