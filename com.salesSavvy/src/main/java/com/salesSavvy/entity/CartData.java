package com.salesSavvy.entity;

public class CartData {
	Long productId;
	int quantity;
	String username;
	public CartData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartData(Long productId, int quantity, String username) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.username = username;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "CartData [productId=" + productId + ", quantity=" + quantity + ", username=" + username + "]";
	}
	
	
}
