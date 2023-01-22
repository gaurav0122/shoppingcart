package com.orderservice.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
	
	@Id
	public String productid;
	@Field
	public String ProductName;

	public Product(String productid, String productName) {
		super();
		this.productid = productid;
		ProductName = productName;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

}
