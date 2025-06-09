package com.salesSavvy.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.salesSavvy.entity.Product;

public interface ProductRepository 
		extends JpaRepository<Product, Long>  {

	Product findByName(String name);

	Product findByCategory(String category);

}
