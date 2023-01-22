package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Category;
import com.product.service.CategoryService;

@RestController
@RequestMapping("/category")
//@CrossOrigin("http://localhost:4200/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/category/{catName}")
	public Category getCategory(@PathVariable("catName") String catName) {
		return categoryService.getByCategoryName(catName);
	}
	
	@GetMapping("/category")
	public List<Category> getAllcategories(){
		return categoryService.getAllCategories();
	}
	
}

