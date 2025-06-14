// PaymentRequest.java
package com.salesSavvy.dto;
public class PaymentRequest {
    public String username;
    public int    amount;    // paise
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentRequest(String username, int amount) {
		super();
		this.username = username;
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
