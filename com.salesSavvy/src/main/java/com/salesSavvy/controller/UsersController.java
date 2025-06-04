package com.salesSavvy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesSavvy.entity.Users;
import com.salesSavvy.service.UsersService;

@RestController
public class UsersController {
	@Autowired
	UsersService service;
	
	@PostMapping("/signUp")
	public String signUp(@RequestBody Users user) {
		String msg = "";
		String username = user.getUsername();
		Users u = service.getUser(username);
		if(u == null) {
			service.signUp(user);
			msg = "User created successfully!";
		}
		else
			msg = "Username already exists!";
		return msg;
	}
}
