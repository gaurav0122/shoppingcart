package com.product.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;


public interface ProductService {
	
	public Product addProduct(Product product, String merchantId);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String productid);
	
	public List<Product> getProductByType(String producttype);
	
	public List<Product> getProductByName( String productName);
	
	public List<Product> getProductByCategory( String category);
	
	public Product updateProducts(Product product,String productId);
	
	public String deleteProductById(String productid);
	
	public List<Product> getProductByMerchantId(String merchantId);

	public List<String> getProductTypeByCategoryId(String categoryId);

	public List<Product> getProductsBySearch(String searchkey);

}
