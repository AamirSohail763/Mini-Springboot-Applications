package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AuthorizationException;
import com.masai.model.LogInModel;
import com.masai.service.LogInServiceImpl;

@RestController
@RequestMapping("/masaifir/user")
public class LoginController {
	
	@Autowired
	private LogInServiceImpl loginService;
	
	
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
