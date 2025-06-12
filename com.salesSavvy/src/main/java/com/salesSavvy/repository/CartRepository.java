package com.salesSavvy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesSavvy.entity.Cart;

public interface CartRepository 
		extends JpaRepository<Cart, Long>{

}
