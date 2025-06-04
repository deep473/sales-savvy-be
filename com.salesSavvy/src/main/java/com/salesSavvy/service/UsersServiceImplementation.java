package com.salesSavvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesSavvy.repository.UsersRepository;

@Service
public class UsersServiceImplementation 
					implements UsersService {
	@Autowired
	UsersRepository repo;
}
