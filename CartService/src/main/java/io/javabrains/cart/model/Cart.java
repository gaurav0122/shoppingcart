package io.javabrains.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {

	@Id
    private String cartId;
	
    private double totalPrice;
    
    @Indexed(unique = true)
    private String userId;
    
    private double totalItems;
    
    private List<Items> items;
    
	
	public Cart() {
        super();


    }
    
	
    public Cart(String cartId, double totalPrice, String userId, double totalItems, List<Items> items) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.userId = userId;
		this.totalItems = totalItems;
		this.items = items;
	}

	public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
    public double getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(double totalItems) {
		this.totalItems = totalItems;
	}


	@Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalPrice=" + totalPrice +
                '}';
    }

   
    
}
