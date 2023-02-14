package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.authexceptions.AuthorizationException;
import com.masai.authmodels.LogInModel;
import com.masai.authservice.LogInModelServiceImpl;

@RestController
@RequestMapping("/masaicab/user")
public class LoginController {
	
	@Autowired
	private LogInModelServiceImpl loginService;
	
	
	@PostMapping("/login")
	public String loginAccountHandler(@RequestBody LogInModel loginData) throws AuthorizationException {
		return loginService.LogIn(loginData);
	}
	
	@PatchMapping("/logout")
	public String logoutAccountHandler(@RequestParam String key) throws AuthorizationException
	{
		return loginService.LogOut(key);
	}
	

}
