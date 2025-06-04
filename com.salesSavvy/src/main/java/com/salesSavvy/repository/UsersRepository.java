package com.salesSavvy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesSavvy.entity.Users;

public interface UsersRepository 
		extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
