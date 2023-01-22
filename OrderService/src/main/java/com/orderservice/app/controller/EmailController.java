package com.orderservice.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.app.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/email/{orderId}")
	public boolean sendEmail(@PathVariable("orderId") String orderId) {
		
		return emailService.getEmail("gauravlavand47@gmail.com", orderId);
	}
	
	
}
