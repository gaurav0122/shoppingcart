package com.orderservice.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.orderservice.app.model.Order;

public interface OrderRepository extends MongoRepository<Order,String> {

	List<Order> findByCustomerId(String customerId);

	
}
