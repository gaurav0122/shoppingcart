package com.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{

	Category findByCategoryName(String catName);
	
}
