// PaymentVerifyRequest.java
package com.salesSavvy.dto;
public class PaymentVerifyRequest {
    public String username;
    public String orderId;
    public String paymentId;
    public String signature;
	public PaymentVerifyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentVerifyRequest(String username, String orderId, String paymentId, String signature) {
		super();
		this.username = username;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.signature = signature;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
