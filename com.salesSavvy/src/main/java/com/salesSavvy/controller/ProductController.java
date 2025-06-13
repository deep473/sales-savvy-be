package com.salesSavvy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.salesSavvy.entity.Cart;
import com.salesSavvy.entity.CartItem;
import com.salesSavvy.entity.Product;
import com.salesSavvy.entity.Users;
import com.salesSavvy.service.CartService;
import com.salesSavvy.service.ProductService;
import com.salesSavvy.service.UsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
public class ProductController {
	@Autowired
	ProductService service;
	
	@Autowired
	UsersService uService;
	
	@Autowired
	CartService cService;
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}
	
	@GetMapping("/searchProduct")
	public Product searchProduct(@RequestParam long id) {
		return service.searchProduct(id);
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam long id) {
		return service.deleteProduct(id);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestBody CartItem item) {
	    Users user = uService.getUser(item.getUsername());
	    if (user == null) return "user not found";

	    Product product = service.searchProduct(item.getProductId());
	    if (product == null) return "product not found";

	    Cart cart = user.getCart();

	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setCartItems(new ArrayList<>()); // ensure cartItems initialized
	        user.setCart(cart);
	        cService.addCart(cart); // Save cart so it gets ID
	    }

	    // Get existing cart items list
	    List<CartItem> items = cart.getCartItems();
	    if (items == null) {
	        items = new ArrayList<>();
	        cart.setCartItems(items);
	    }

	    // Check for existing item
	    boolean found = false;
	    for (CartItem ci : items) {
	        if (ci.getProduct().getId().equals(product.getId())) {
	            ci.setQuantity(ci.getQuantity() + item.getQuantity());
	            found = true;
	            break;
	        }
	    }

	    // If not already in cart, add as new item
	    if (!found) {
	        CartItem newItem = new CartItem();
	        newItem.setCart(cart);
	        newItem.setProduct(product);
	        newItem.setQuantity(item.getQuantity());
	        items.add(newItem);
	    }

	    cService.addCart(cart); // persists both Cart and CartItems via cascade
	    return "cart added";
	}

	
	@GetMapping("/getCart/{username}")
	public List<CartItem> getCart(@PathVariable String username) {
	    Users user = uService.getUser(username);
	    if (user == null || user.getCart() == null)
	        return new ArrayList<>();

	    return user.getCart().getCartItems();
	}


}
