package io.javabrains.cart.service.impl;

import io.javabrains.cart.model.Cart;
import io.javabrains.cart.model.Items;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;



public interface CartService {

    public Cart getCartById(String cartId);
    
    public Cart updateCart(Cart cart,String cartId);
    
    public List<Cart> getAllCarts();
    
    public double CartTotal(String cartId);
    
    public Cart addCart(Cart cart, String userId);
    
	public Cart addItemInCart(String userId, String productId);
	
	public Cart removeProduct(String cartId, String productId);
	
	public Cart addQuantityOfProduct(String cartId, String productId);
	
	public Cart subQuantityOfProduct(String cartId, String productId);
	
	public Cart getCartByuserId(String userId);
	
	public Cart removeAllItemInCart(String cartId);
}
