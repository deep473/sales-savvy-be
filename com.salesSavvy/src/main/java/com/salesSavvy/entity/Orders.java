package com.salesSavvy.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Orders {

    @Id                         // Razorpay order-id (e.g. "order_LuF...")
    private String id;

    private int    amount;      // paise
    private String currency;    // "INR"
    private String status;      // CREATED | PAID
    private String receipt;     // optional

    private String paymentId;   // Razorpay payment-id after success

    @ManyToOne
    private Users user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;   // snapshot of cart at checkout

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", amount=" + amount + ", currency=" + currency + ", status=" + status
				+ ", receipt=" + receipt + ", paymentId=" + paymentId + ", user=" + user + ", items=" + items + "]";
	}

    
}
