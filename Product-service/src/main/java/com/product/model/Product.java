package com.product.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
	
	@Id
	private String productid;
	
	@Field
	private String productType;
	
	@Field
	private String productName;
	
	@DBRef
	private Category category;

	@Field
	private List<String> image;
	
	@Field
	private double price;
	
	@Field
	private String description;  
	
	@Field
	private Map<String, String> specification;  

	@Field
	private String merchantId;  
	
	@Field
	private String merchantName;

	public Product() {
		
	}
	

	public Product(String productid, String productType, String productName, Category category, List<String> image,
			double price, String description, Map<String, String> specification, String merchantId,
			String merchantName) {
		super();
		this.productid = productid;
		this.productType = productType;
		this.productName = productName;
		this.category = category;
		this.image = image;
		this.price = price;
		this.description = description;
		this.specification = specification;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
	}


	public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getProductType() {
		return productType;
	}


	public void setProducttype(String productType) {
		this.productType = productType;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<String> getImage() {
		return image;
	}


	public void setImage(List<String> image) {
		this.image = image;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Map<String, String> getSpecification() {
		return specification;
	}


	public void setSpecification(Map<String, String> specification) {
		this.specification = specification;
	}


	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


}
