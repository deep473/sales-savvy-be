package com.salesSavvy.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String username;
    String email;
    String password;
    String gender;
    String dob;
    String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference        // serialise Users → Cart (inverse of @JsonBackReference)
    private Cart cart;

    /* ---------- constructors ---------- */
    public Users() {}

    public Users(Long id, String username, String email, String password,
                 String gender, String dob, String role, Cart cart) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.role = role;
        this.cart = cart;
    }

    /* ---------- getters / setters ---------- */
    public Long getId()                { return id; }
    public void setId(Long id)         { this.id = id; }

    public String getUsername()        { return username; }
    public void setUsername(String u)  { this.username = u; }

    public String getEmail()           { return email; }
    public void setEmail(String e)     { this.email = e; }

    public String getPassword()        { return password; }
    public void setPassword(String p)  { this.password = p; }

    public String getGender()          { return gender; }
    public void setGender(String g)    { this.gender = g; }

    public String getDob()             { return dob; }
    public void setDob(String d)       { this.dob = d; }

    public String getRole()            { return role; }
    public void setRole(String r)      { this.role = r; }

    public Cart getCart()              { return cart; }
    public void setCart(Cart cart)     { this.cart = cart; }

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", email=" + email +
               ", password=" + password + ", gender=" + gender + ", dob=" + dob +
               ", role=" + role + ", cart=" + cart + "]";
    }
}
