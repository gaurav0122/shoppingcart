package io.javabrains.cart.service.impl;

import io.javabrains.cart.Repository.CartRepository;
import io.javabrains.cart.model.Cart;
import io.javabrains.cart.model.Items;
import io.javabrains.cart.model.Product;
import io.javabrains.cart.model.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
	
	
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public Cart addCart(Cart cart,String userId) {
    	UserProfile profile = null;
    	try {
    		profile = restTemplate.getForObject("http://profile-service/user/user1/"+userId,
    										UserProfile.class);
		} catch (Exception e) {
			
		}
    	
    	if(profile == null) {
    		return cart;
    	}
    	cart.setUserId(profile.getId());
    	cart.setTotalPrice(0);
    	cart.setTotalItems(0);
    	return cartRepository.save(cart);
    }
   
    @Override
    public Cart getCartById(String cartId) {
     Optional<Cart> cartOptional = cartRepository.findById(cartId);
		return cartOptional.get();
    }


    @Override
    public Cart updateCart(Cart cart,String cartId) {
        Cart cartDb=cartRepository.findById(cartId).get();
        
        if(cart.getTotalPrice() != 0) {
            cartDb.setTotalPrice(cart.getTotalPrice());
        }

        return cartRepository.save(cartDb);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public double CartTotal(String cartId) {
    	Cart cart=cartRepository.findById(cartId).get();
    	double total=0;
    	for(Items item:cart.getItems()) {
    		total+=item.getPrice();
    	}
        return total;
      
    }


	@Override
	public Cart addItemInCart(String userId, String productId) {
		Items item = new Items();
		Cart cart = getCartByuserId(userId);
		List<Items> list = cart.getItems();
		if(list == null)
			list = new ArrayList<>();
		
		Product product = restTemplate.getForObject("http://product-service/product/allproduct/"+productId
													,Product.class);
		
		
		long count = list.stream().filter(it -> it.getProductId().equals(productId)).count();
		if(count > 0)
			throw new RuntimeException("Item already present ");
		
		item.setProductId(product.getProductid());
		item.setProductName(product.getProductName());
		try {
			item.setProductImg(product.getImage().get(0));
		} catch (Exception e) {
			item.setProductImg("");
		}
		item.setPrice(product.getPrice());
		item.setQuantity(1);
		
		list.add(item);
		cart.setTotalPrice(list.stream().map(c->c.getPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		cart.setItems(list);
		cart.setTotalItems(list.size());
		return cartRepository.save(cart);
	}

	@Override
	public Cart removeProduct(String cartId, String productId) {
		
		Cart cart = cartRepository.findById(cartId).get();
		List<Items> list = cart.getItems();
		if(list.isEmpty())
			throw new RuntimeException("item is not there");
		
		list.remove(list.stream().filter(i->i.getProductId().equals(productId)).findFirst().get());
		cart.setTotalPrice(list.stream().map(c->c.getPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		cart.setTotalItems(list.size());
		return cartRepository.save(cart);
		
	}


	@Override
	public Cart addQuantityOfProduct(String cartId, String productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Items> list = cart.getItems();
		if(list.isEmpty())
			throw new RuntimeException("item is not there");
//		for(Items i :list) {
//			if(i.getProductId().equals(productId))
//				i.setQuantity(i.getQuantity() + 1) ;
//		}
		list.stream().map(i->{if(i.getProductId().equals(productId)) {
					if(i.getQuantity()<10) {
						i.setQuantity(i.getQuantity()+1);
					}
					}
					return i;	
					}).collect(Collectors.toList());
		
		cart.setTotalPrice(list.stream().map(c->c.getPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		cart.setTotalItems(list.size());
		return cartRepository.save(cart);
	}

	@Override
	public Cart subQuantityOfProduct(String cartId, String productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Items> list = cart.getItems();
		if(list.isEmpty())
			throw new RuntimeException("item is not there");
		list.stream().map(i->{if(i.getProductId().equals(productId)) {
				if(i.getQuantity()>1)
					i.setQuantity(i.getQuantity()-1);			
				}
			return i;	
			}).collect(Collectors.toList());
		
		cart.setTotalPrice(list.stream().map(c->c.getPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		cart.setTotalItems(list.size());
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartByuserId(String userId) {
		return cartRepository.findByUserId(userId);
	}

	@Override
	public Cart removeAllItemInCart(String cartId) {
		Cart cart = cartRepository.findById(cartId).get();
		cart.getItems().clear();
		cart.setTotalItems(0);
		cart.setTotalPrice(0.0D);
		return cartRepository.save(cart);
	}
	
	
    


}
	