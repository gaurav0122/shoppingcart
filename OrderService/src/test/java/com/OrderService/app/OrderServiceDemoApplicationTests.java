package com.OrderService.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.orderservice.app.controller.OrderController;
import com.orderservice.app.model.Address;
import com.orderservice.app.model.Items;
import com.orderservice.app.model.Order;
import com.orderservice.app.service.OrderService;

@SpringBootTest
class OrderServiceDemoApplicationTests {

	@InjectMocks
	private OrderController orderController;
	
	@Mock
	private OrderService orderService;
	
	Order order;
	Address address;
	List<Items> items;
	
	Order placeorder;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		placeorder = new Order();
		placeorder.setModeOfPayment("Cash-On-Delivery");
		placeorder.setPaymentStatus(true);
		
		address = new Address();
		address.setHouseNumber(12);
		address.setColonyName("ABC colony");
		address.setCity("Pune");
		address.setPinCode(413133L);
		address.setState("Maharashtra");
		address.setStreetName("xyz street");
		items = new ArrayList<>();
		Items item1 = new Items("Product1",1000,2);
		item1.setProductId("pid1");
		item1.setProductImg("pImgUrl");
		Items item2 = new Items("Product2",1000,2);
		item2.setProductId("pid2");
		item2.setProductImg("pImgUrl");
		items.add(item1);
		items.add(item2);
		
		
		order = new Order("oid1",LocalDate.now(),"cid",4000d,true,"Cash-On-Delivery",
															"Placed",2,items,address);
		
	}
	

	@Test
	@org.junit.jupiter.api.Order(1)
	void placeOrderBodyNotNull() {
		assertNotNull(placeorder,"order is null");
	}
	
	@Test
	@org.junit.jupiter.api.Order(2)
	void placeOrderBodypaymentStatusNotNull() {
		assertNotNull(placeorder.getPaymentStatus(),"order is null");
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	void placeOrderBodyModeOfPaymentNotNull() {
		assertNotNull(placeorder.getModeOfPayment(),"order is null");
	}
	
	@Test
	@org.junit.jupiter.api.Order(4)
	void placeOrderNotNull() {
		when(orderService.addOrder("cartId", placeorder)).thenReturn(order);
		Order placedOrder = orderController.addOrder("cartId", placeorder);
		assertNotNull(placedOrder,"order is null");
	}
	
	@Test
	@org.junit.jupiter.api.Order(5)
	void placeOrderOrderDatecheck() {
		when(orderService.addOrder("cartId", placeorder)).thenReturn(order);
		
		Order placedOrder = orderController.addOrder("cartId", placeorder);
		
		assertNotNull(placedOrder.getOrderDate(),"order date is null");
		
		assertEquals(LocalDate.now(), placedOrder.getOrderDate());
	}
	
	@Test
	@org.junit.jupiter.api.Order(6)
	void placeOrderAmountPaidcheck() {
		when(orderService.addOrder("cartId", placeorder)).thenReturn(order);
		
		Order placedOrder = orderController.addOrder("cartId", placeorder);
		
		assertNotNull(placedOrder.getAmountPaid(),"Amount paid is null");
		
		assertEquals(order.getAmountPaid(), placedOrder.getAmountPaid());
	}
	
	@Test
	@org.junit.jupiter.api.Order(7)
	void placeOrderItemscheck() {
		when(orderService.addOrder("cartId", placeorder)).thenReturn(order);
		
		Order placedOrder = orderController.addOrder("cartId", placeorder);
		
		assertNotNull(placedOrder.getItems(),"list of items is null");
		
		assertEquals(order.getItems().size(), placedOrder.getItems().size());
	}
	
	@Test
	@org.junit.jupiter.api.Order(8)
	void placeOrderAddresscheck() {
		when(orderService.addOrder("cartId", placeorder)).thenReturn(order);
		
		Order placedOrder = orderController.addOrder("cartId", placeorder);
		
		assertNotNull(placedOrder.getAddress(),"Address is null");
		
		assertEquals(order.getAddress().getHouseNumber(), placedOrder.getAddress().getHouseNumber());
	}
	
	
	@Test
	@org.junit.jupiter.api.Order(9)
	void getOrderById() {
		when(orderService.getOrderById("oid1")).thenReturn(order);
		
		Order placedOrder = orderController.getOrderById("oid1");
		
		assertNotNull(placedOrder.getAddress(),"order is null");
		
		assertEquals(order.getOrderId(), placedOrder.getOrderId());
	}
	
	@Test
	@org.junit.jupiter.api.Order(10)
	void deleteOrderById() {
		when(orderService.deleteOrderById("oid1")).thenReturn("Order Deleted  or Cancled Succesfully");
		
		String deleteOrder = orderController.deleteOrderById("oid1");
		
		assertNotNull(deleteOrder,"order is null");
		
		assertEquals("Order Deleted  or Cancled Succesfully", deleteOrder);
	}
	
	@Test
	@org.junit.jupiter.api.Order(11)
	void getOrderByUserId() {
		List<Order> list = new ArrayList<>();
		list.add(order);
		when(orderService.getOrdersByUserId("cid")).thenReturn(list);
		
		List<Order> placedOrder = orderController.getOrderByUserId("cid");
		
		assertNotNull(placedOrder,"order is null");
		
		assertEquals(list.size(), placedOrder.size());
	}
	
	@Test
	@org.junit.jupiter.api.Order(12)
	void cancelOrderByOrderId() {
		order.setOrderStatus("Cancelled");
		when(orderService.cancelOrderByOrderId("oid1")).thenReturn(order);
		
		Order placedOrder = orderController.cancelOrderByOrderId("oid1");
		
		assertNotNull(order.getOrderStatus(),"order is null");
		
		assertEquals(order.getOrderStatus(), placedOrder.getOrderStatus());
	}
	
	
}
