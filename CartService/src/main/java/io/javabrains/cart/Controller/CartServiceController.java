package io.javabrains.cart.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.cart.model.Cart;
import io.javabrains.cart.model.Product;
import io.javabrains.cart.service.impl.CartService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cart")
//@CrossOrigin("http://localhost:4200")
public class CartServiceController  {
  
	@Autowired
    private CartService cartService;
	
	Logger logger = LoggerFactory.getLogger(CartServiceController.class);

	@ApiOperation("creating cart for the user")
	@PostMapping("/addcart/{userId}") // add cart
    public Cart addCart(@PathVariable("userId") String userId,@RequestBody Cart cart) {
		logger.trace("Create cart method accessed");
        return cartService.addCart(cart,userId);
    }
	    
	@ApiOperation("Add item to the cart")
	@PostMapping("/additem/{userId}/{productId}")
	public Cart addItemCart(@PathVariable String userId,@PathVariable("productId") String productId) {
		logger.trace("add item to cart method accessed");
		return cartService.addItemInCart(userId,productId);
	}
	
	@ApiOperation("get the cart details by cartId")
    @GetMapping("/cart/{cartId}") //get details of the cart By cartId
    public Cart getCartById(@PathVariable String cartId) {
		logger.trace("Get details by cart id method accessed");
        return cartService.getCartById(cartId);

    }
	
	@ApiOperation("get the cart details by userId")
    @GetMapping("/cart/user/{userId}") //get details of the cart By cartId
    public Cart getCartByuserId(@PathVariable String userId) {
		logger.trace("Get details by cart id method accessed");
        return cartService.getCartByuserId(userId);

    }

	@ApiOperation("get all carts")
    @GetMapping("/cart") //taking data from the cart
    public List<Cart> getAllCarts() {
		logger.trace("Get all carts method accessed");
        List<Cart> cartList=cartService.getAllCarts();
        return cartList;
    }
    
    @ApiOperation("update cart")
    @PutMapping("/cart/{CartId}") //updating
   
    public Cart updateCart
            (@RequestBody Cart cart,@PathVariable("CartId")String cartId ) {

        return cartService.updateCart(cart,cartId);

    }
    
    @ApiOperation("get the cart total amount")
    @GetMapping("/cartTotal/{cartId}")
    public double CartTotal(@PathVariable String cartId)
    {
    	logger.trace("Get all cart total method accessed");
    	double total = cartService.CartTotal(cartId);
        return total;
    }
    
    @ApiOperation("remove item from the cart")
    @PutMapping("/cart/remove/{cartId}/{productId}")
    public Cart removeitem(@PathVariable("cartId") String cartId, @PathVariable("productId") String productId) {
    	logger.trace("remove item from the cart method accessed");
    	return cartService.removeProduct(cartId, productId);
    }
    
    @ApiOperation("Add the quantity of the product")
    @PutMapping("/cart/addquantity/{cartId}/{productId}")
    public Cart addQuantity(@PathVariable("cartId") String cartId, @PathVariable("productId") String productId) {
    	return cartService.addQuantityOfProduct(cartId, productId);
    }
   
    @ApiOperation("decrease the quantity of the product")
    @PutMapping("/cart/subquantity/{cartId}/{productId}")
    public Cart subQuantity(@PathVariable("cartId") String cartId, @PathVariable("productId") String productId) {
    	return cartService.subQuantityOfProduct(cartId, productId);
    }
    
    
    @ApiOperation("remove all items from the cart")
    @PutMapping("/cart/removeall/{cartId}")
    public Cart removeAllItemInCart(@PathVariable("cartId") String cartId) {
    	logger.trace("remove item from the cart method accessed");
    	return cartService.removeAllItemInCart(cartId);
    }
}


