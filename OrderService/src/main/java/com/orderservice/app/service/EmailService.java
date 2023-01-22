package com.orderservice.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;
import com.orderservice.app.model.Order;
import com.orderservice.app.model.UserProfile;
import com.orderservice.app.repository.OrderRepository;

@Service
public class EmailService {
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	 @Value("${spring.mail.username}") 
	 private String sender;
	
	public boolean getEmail(String receiver,String OrderId) {
		
		Order order = orderRepository.findById(OrderId).get();
		
		UserProfile profile = restTemplate.getForObject("http://profile-service/user/user1/"
						+order.getCustomerId(),UserProfile.class);
		Integer a = order.getAddress().getHouseNumber();
		String address = a.toString()+", "
						+order.getAddress().getStreetName()+", "
						+order.getAddress().getColonyName()+", "
						+order.getAddress().getCity()+", "
						+order.getAddress().getState()+", "
						+order.getAddress().getPinCode().toString()+".";
		Integer totalItems = order.getItems().size();
		String message ="Dear, " 
							+ profile.getFullName() 
							+ "\n\n"
							+ "Thank you for your order.\n"
							+ "We truly value our loyal customers. Thanks for making us who we are!\n\n"
							+ "Here’s order summary for your order: \n"
							+ "Order Id :" + order.getOrderId()+"\n"
							+ "Order Date : "+ order.getOrderDate()+"\n"
							+ "Total Items : "+ totalItems.toString()+"\n"
							+ "amountPaid : "+ order.getAmountPaid() +"\n"
							+ "Payment Mode : " + order.getModeOfPayment() + "\n"
							+ "Address : " + address ;
		
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(receiver);
		mailMessage.setFrom(sender);
		mailMessage.setText(message);
        mailMessage.setSubject("Shopitto : Order placed successfully");
        
        javaMailSender.send(mailMessage);
        return true;
	}
}
