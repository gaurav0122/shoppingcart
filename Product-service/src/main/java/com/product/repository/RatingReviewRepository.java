package com.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.model.ReviewAndRating;

public interface RatingReviewRepository extends MongoRepository<ReviewAndRating, String>{

	List<ReviewAndRating> findByProductId(String productId);

}
