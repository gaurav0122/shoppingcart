package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Category;
import com.product.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
		
	}

	public Category getByCategoryName(String catName) {
		return categoryRepository.findByCategoryName(catName);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
