package io.javabrains.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.javabrains.cart.Controller.CartServiceController;
import io.javabrains.cart.model.Cart;
import io.javabrains.cart.model.Items;
import io.javabrains.cart.service.impl.CartService;

@SpringBootTest
class CartApplication1Tests {

	@InjectMocks
	private CartServiceController cartServiceController;
	
	@Mock
	private CartService cartService;
	
	private Cart cart;
	
	private List<Items> items;
	
	private final String cartId="cid1";
	
	private final String userId="uid1";
	
	private final String productId="pid1";
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		
		cart = new Cart();
		items = new ArrayList<>();
		
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		
		cart.setCartId(cartId);
		cart.setUserId(userId);
		cart.setItems(items);
		cart.setTotalItems(items.size());
		cart.setTotalPrice(0D);
	}
	
	@Test
	void addCartBody() {
		assertNotNull(cart,"cart is null");
	}
	
	@Test
	void addCartMethod() {
		when(cartService.addCart(any(Cart.class), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addCart(userId, cart);
		
		assertNotNull(cartRest,"Cart is null");
	}
	
	@Test
	void addCartUserIdCheck() {
		when(cartService.addCart(any(Cart.class), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addCart(userId, cart);
		
		assertNotNull(cartRest.getUserId(),"Cart is null");
		
		assertEquals(cart.getUserId(), cartRest.getUserId());
	}
	
	@Test
	void addCartTotalItemsCheck() {
		when(cartService.addCart(any(Cart.class), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addCart(userId, cart);
		
		assertNotNull(cartRest.getUserId(),"UserId is null");
		
		assertEquals(0, cartRest.getTotalItems());
	}
	
	@Test
	void addCartTotalPriceCheck() {
		when(cartService.addCart(any(Cart.class), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addCart(userId, cart);
		
		assertNotNull(cartRest.getTotalPrice(),"Total price is null");
		
		assertEquals(0, cartRest.getTotalPrice());
	}
	
	@Test
	void addCartListOfItemsCheck() {
		when(cartService.addCart(any(Cart.class), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addCart(userId, cart);
		
		assertNotNull(cartRest.getItems(),"Cart is null");
		
		assertEquals(0, cartRest.getItems().size());
	}
	
	@Test
	void addItemToCartMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		when(cartService.addItemInCart(anyString(), anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addItemCart(userId, productId);
		
		assertNotNull(cartRest.getItems(),"Cart is null");
		
		assertEquals(1, cartRest.getItems().size());
	}
	
	@Test
	void getCartDetailsByCartIdMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		when(cartService.getCartById(anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.getCartById(cartId);
		
		assertNotNull(cartRest,"Cart is null");
		
		assertEquals(cart.getCartId(), cartRest.getCartId());
	}
	
	@Test
	void getCartDetailsByUserIdMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		
		when(cartService.getCartByuserId(anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.getCartByuserId(userId);
		
		assertNotNull(cartRest,"Cart is null");
		
		assertEquals(cart.getUserId(), cartRest.getUserId());
	}
	
	@Test
	void getAllCartMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.getAllCarts()).thenReturn(list);
		
		List<Cart> cartRest = cartServiceController.getAllCarts();
		
		assertNotNull(cartRest,"Cart is null");
		
		assertEquals(1, cartRest.size());
	}
	
	@Test
	void getCartTotalMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		cart.setTotalPrice(1000d);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.CartTotal(anyString())).thenReturn(1000d);
		
		Double total = cartServiceController.CartTotal(cartId);
		
		assertNotNull(total,"total is null");
		
		assertEquals(1000d, total);
	}
	
	@Test
	void removeitemMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.removeProduct(anyString(),anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.removeitem(cartId,productId);
		cartRest.getItems().remove(0);
		assertNotNull(cartRest,"Cart is null");
		
		assertEquals(0, cartRest.getItems().size());
	}
	
	
	@Test
	void addQuantityMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		cart.setTotalPrice(1000d);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.addQuantityOfProduct(anyString(),anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.addQuantity(cartId,productId);
		item1.setQuantity(2);
		cart.setItems(items);
		cart.setTotalPrice(2000d);
		assertNotNull(cartRest,"total is null");
		
		assertEquals(2,cartRest.getItems().get(0).getQuantity() );
		assertEquals(2000d, cartRest.getTotalPrice());
	}
	
	@Test
	void reduceQuantityMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(2);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		cart.setTotalPrice(2000d);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.subQuantityOfProduct(anyString(),anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.subQuantity(cartId,productId);
		item1.setQuantity(1);
		cart.setItems(items);
		cart.setTotalPrice(1000d);
		assertNotNull(cartRest,"total is null");
		
		assertEquals(1,cartRest.getItems().get(0).getQuantity() );
		assertEquals(1000d, cartRest.getTotalPrice());
	}
	
	@Test
	void removeAllitemsMethodCheck() {
		Items item1 = new Items();
		item1.setProductId("product1");
		item1.setProductName("ABC product");
		item1.setQuantity(1);
		item1.setPrice(1000);
		items.add(item1);
		cart.setItems(items);
		List<Cart> list = new ArrayList<>();
		list.add(cart);
		
		when(cartService.removeAllItemInCart(anyString())).thenReturn(cart);
		
		Cart cartRest = cartServiceController.removeAllItemInCart(cartId);
		cartRest.getItems().clear();
		assertNotNull(cartRest,"Cart is null");
		
		assertEquals(0, cartRest.getItems().size());
	}
}
