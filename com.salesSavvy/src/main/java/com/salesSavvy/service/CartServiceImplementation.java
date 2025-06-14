package com.salesSavvy.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesSavvy.entity.Cart;
import com.salesSavvy.entity.CartItem;
import com.salesSavvy.entity.Users;
import com.salesSavvy.repository.CartRepository;
import com.salesSavvy.repository.UsersRepository;

@Service
public class CartServiceImplementation implements CartService {

    private final CartRepository  cartRepo;
    private final UsersRepository userRepo;

    /* ---------- constructor injection ---------- */
    public CartServiceImplementation(CartRepository cartRepo,
                                     UsersRepository userRepo) {
        this.cartRepo  = cartRepo;
        this.userRepo  = userRepo;
    }

    /* === CartService methods === */

    @Override
    public void addCart(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public void clearCart(String username) {
        Users u = userRepo.findByUsername(username);
        if (u != null && u.getCart() != null) {
            u.getCart().getCartItems().clear();
            cartRepo.save(u.getCart());
        }
    }

    @Override
    public List<CartItem> getItems(String username) {
        Users u = userRepo.findByUsername(username);
        if (u == null || u.getCart() == null) {
            return Collections.emptyList();
        }
        return u.getCart().getCartItems();
    }
    
    @Override
    public List<CartItem> cloneItems(String username) {
        return getItems(username).stream().map(src -> {
            CartItem copy = new CartItem();
            copy.setProduct(src.getProduct());   // keep the same product ref
            copy.setQuantity(src.getQuantity());
            copy.setCart(null);                  // detach from cart
            return copy;
        }).toList();
    }

}
