package com.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.model.Category;
import com.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByCategoryId(String id);

	List<Product> findByProductName(String productName);

	List<Product> findByProductType(String producttype);

	List<Product> findByMerchantId(String merchantId);

	List<Product> findAllByCategoryId(String id);



	

}
