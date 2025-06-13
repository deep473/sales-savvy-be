package com.salesSavvy.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference           // stops CartItem → Cart → CartItem loop
    private Cart cart;

    @ManyToOne
    @JsonIgnoreProperties("cart") // in case old Product rows still have cart_id
    private Product product;

    private int quantity;

    /* ---------- transient helpers (sent by React) ---------- */
    @Transient private String username;
    @Transient private Long   productId;

    /* ---------- constructors ---------- */
    public CartItem() {}

    public CartItem(Long id, Cart cart, Product product, int quantity,
                    String username, Long productId) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.username = username;
        this.productId = productId;
    }

    /* ---------- getters / setters ---------- */
    public Long getId()                    { return id; }
    public void setId(Long id)             { this.id = id; }

    public Cart getCart()                  { return cart; }
    public void setCart(Cart cart)         { this.cart = cart; }

    public Product getProduct()            { return product; }
    public void setProduct(Product product){ this.product = product; }

    public int getQuantity()               { return quantity; }
    public void setQuantity(int quantity)  { this.quantity = quantity; }

    public String getUsername()            { return username; }
    public void setUsername(String u)      { this.username = u; }

    public Long getProductId()             { return productId; }
    public void setProductId(Long pId)     { this.productId = pId; }
}
