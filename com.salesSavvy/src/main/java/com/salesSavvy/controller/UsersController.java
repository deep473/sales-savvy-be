package com.salesSavvy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.salesSavvy.service.UsersService;

@RestController
public class UsersController {
	@Autowired
	UsersService service;
}
