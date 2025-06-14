package com.salesSavvy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesSavvy.entity.Orders;
public interface OrderRepository extends JpaRepository<Orders,String>{}
