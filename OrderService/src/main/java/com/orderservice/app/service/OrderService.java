package com.orderservice.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.orderservice.app.model.Order;


public interface OrderService {

	//public Order addOrder(Cart cart, Order order);

	public List<Order> getAllOrder();

	public Order getOrderById(String orderId);

	public String deleteOrderById(String OrderId);

	public Order addOrder(String cartId, Order order);

	public List<Order> getOrdersByUserId(String userId);

	public Order cancelOrderByOrderId(String orderId);

	
	
	

}
