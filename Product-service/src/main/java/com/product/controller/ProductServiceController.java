package com.product.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.service.ProductService;
import com.product.service.ProductServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
//@CrossOrigin("http://localhost:4200/")
public class ProductServiceController{

	Logger logger = LoggerFactory.getLogger(ProductServiceController.class);
	
	@Autowired
	private ProductService productService;

	
	//Post all 
	@PostMapping("/allproduct/{merchantId}")
	@ApiOperation("Add product method")
	public Product addProduct(@RequestBody Product product,
			@PathVariable("merchantId") String merchantId) {
		logger.trace("Add product method accessed");
		
	   return productService.addProduct(product,merchantId);
	}
    
	//get all product
	@GetMapping("/allproduct")
	@ApiOperation("Get all products method")
	public List<Product> getAllProducts() {
		logger.trace("Get all product method accessed");
		List<Product> productList = productService.getAllProducts();
	
		return productList;
	}
	
	
	//get Product By product Id
	@GetMapping("/allproduct/{productId}")
	@ApiOperation("Get product by product id method")
	public Product getProductById(@PathVariable("productId")String productId) {
		logger.trace("Get product by product id method accessed");
		Product product = productService.getProductById(productId);
		
		return product;
	}

	
	
	//get Product By Product type

	@GetMapping("/allproduct/type/{producttype}")
	@ApiOperation("Get products by product type method")
	public List<Product> getProductByType(@PathVariable ("producttype") String producttype) {
		// TODO Auto-generated method stub
		logger.trace("Get products by product type method accessed");
		return productService.getProductByType(producttype);
	}
 
    //get product By Name
	@GetMapping("/allproduct/name/{productname}")
	@ApiOperation("Get products by product name method")
	public List<Product> getProductByName(@PathVariable("productname") String productname) {
		logger.trace("Get products by product name method accessed");
		return productService.getProductByName(productname);
	}

	//get Products By Category
	@GetMapping("/allproduct/category/{category}")
	@ApiOperation("Get products by product category method")
	public List<Product> getProductByCategory(@PathVariable("category") String category) {
		logger.trace("Get products by product category method accessed");
		return productService.getProductByCategory(category);
	}

	
	//Update Product by Id
	@PutMapping("/allproduct/{productId}")
	@ApiOperation("Update product by product id method")
	public Product updateProducts(@RequestBody Product product,
			                           @PathVariable("productId") String productId ) {
		logger.trace("Update product by product id method accessed");

		return productService.updateProducts(product,productId) ;
	}

	//Delete Product By id
	@DeleteMapping("/allproduct/{productId}")
	@ApiOperation("Delete product by product id method")
	public String deleteProductById(@PathVariable("productId") String productId) {
		logger.trace("Delete product by product id method accessed");
		return productService.deleteProductById(productId);
	}

	// get Products by merchantId
	@GetMapping("/allproduct/merchantId/{merchantId}")
	@ApiOperation("Get products by merchant")
	public List<Product> getProductByMerchantId(@PathVariable String merchantId){
		return productService.getProductByMerchantId(merchantId);
	}
	
	@GetMapping("/productType/byCategory/{cid}")
	public List<String> getProductTypeByCategory(@PathVariable("cid") String categoryId){
		return productService.getProductTypeByCategoryId(categoryId);
		
	}
	
	@GetMapping("/products/bySearch/{searchkey}")
	public List<Product> getProductsBySearch(@PathVariable("searchkey") String searchkey){
		return productService.getProductsBySearch(searchkey);
		
	}
	
	

}
