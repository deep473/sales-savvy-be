package com.salesSavvy.service;

import com.salesSavvy.entity.Users;

public interface UsersService {
	void signUp(Users user);
	Users getUser(String username);
}
