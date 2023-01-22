package com.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Category {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String categoryName;

	private String categoryImg;
	
	public Category() {
		
	}

	
	
	public Category(String id, String categoryName, String categoryImg) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryImg = categoryImg;
	}



	public String getCategoryImg() {
		return categoryImg;
	}


	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
