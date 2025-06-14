package com.salesSavvy.service;

import java.util.List;

import com.salesSavvy.entity.Cart;
import com.salesSavvy.entity.CartItem;

public interface CartService {
	void addCart(Cart cart);

	void clearCart(String username);

	List<CartItem> getItems(String username);
	
	List<CartItem> cloneItems(String username);

}
