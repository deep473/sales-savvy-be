package com.salesSavvy.service;

import java.util.List;

import com.salesSavvy.entity.Product;

public interface ProductService {
	String addProduct(Product product);
	Product searchProduct(long id);
	Product searchProduct(String name);
	Product searchProductByCategory(String category);
	String updateProduct(Product product);
	String deleteProduct(long id);
	
	List<Product> getAllProducts();
}
