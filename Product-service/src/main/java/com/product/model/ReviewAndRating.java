package com.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReviewAndRating {
	
	@Id
	private String reviewId;
	
	private double ratings;

	private String reviewTitle;
	
	private String reviewText;
	
	private String productId;
	
	private String userId;
	
	private String userName; 

	
	public ReviewAndRating() {
		super();
	}


	public ReviewAndRating(String reviewId, double ratings, String reviewText, String reviewTitle, String productId,
			String userId, String userName) {
		super();
		this.reviewId = reviewId;
		this.ratings = ratings;
		this.reviewText = reviewText;
		this.reviewTitle = reviewTitle;
		this.productId = productId;
		this.userId = userId;
		this.userName = userName;
	}


	public String getReviewId() {
		return reviewId;
	}


	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}


	public double getRatings() {
		return ratings;
	}


	public void setRatings(double ratings) {
		this.ratings = ratings;
	}


	public String getReviewText() {
		return reviewText;
	}


	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}


	public String getReviewTitle() {
		return reviewTitle;
	}


	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
