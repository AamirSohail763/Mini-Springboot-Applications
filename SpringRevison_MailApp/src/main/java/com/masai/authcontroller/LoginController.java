package com.masai.authcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.LogInModel;
import com.masai.authservice.LogInModelServiceImpl;

@RestController
public class LoginController {
	
	@Autowired
	private LogInModelServiceImpl loginService;
	
	@PostMapping("/login")
	public String loginHandler(@RequestBody LogInModel loginData) throws AuthorizationException {
		return loginService.LogIn(loginData);
	}
	
	@PatchMapping("/logout")
	public String logOutFromAccount(@RequestParam String key) throws AuthorizationException
	{
		return loginService.LogOut(key);
	}
	

}
