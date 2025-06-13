package com.salesSavvy.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonBackReference          // prevents JSON loop Users → Cart → Users…
    private Users user;

    @OneToMany(
        mappedBy = "cart",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference       // serialise Cart → CartItems
    private List<CartItem> cartItems = new ArrayList<>();

    /* ---------- constructors ---------- */

    public Cart() {}

    public Cart(Long id, Users user, List<CartItem> cartItems) {
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;
    }

    /* ---------- getters / setters ---------- */

    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public Users getUser()           { return user; }
    public void setUser(Users user)  { this.user = user; }

    public List<CartItem> getCartItems()                 { return cartItems; }
    public void setCartItems(List<CartItem> cartItems)   { this.cartItems = cartItems; }
}
