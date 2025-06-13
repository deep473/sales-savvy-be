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
	            user.setCart(cart);
	            cService.addCart(cart);  // persist empty cart first
	        }

	        List<CartItem> items = cart.getCartItems();
	        if (items == null) items = new ArrayList<>();

	        boolean found = false;
	        for (CartItem ci : items) {
	            if (ci.getProduct().getId().equals(product.getId())) {
	                ci.setQuantity(ci.getQuantity() + item.getQuantity());
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            CartItem newItem = new CartItem();
	            newItem.setCart(cart);
	            newItem.setProduct(product);
	            newItem.setQuantity(item.getQuantity());
	            items.add(newItem);
	        }

	        cart.setCartItems(items);
	        cService.addCart(cart);  // cascade saves CartItems
	        return "cart added";
	    }

	    @GetMapping("/getCart/{username}")
	    public List<CartItem> getCart(@PathVariable String username) {
	        Users u = uService.getUser(username);
	        if (u == null || u.getCart() == null) return new ArrayList<>();
	        return u.getCart().getCartItems();
	    }


}
