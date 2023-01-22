package com.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.controller.ProductServiceController;
import com.product.model.Category;
import com.product.model.Product;
import com.product.service.ProductService;

@SpringBootTest
public class productTest {

	@InjectMocks
	ProductServiceController productServiceController;
	
	@Mock
	ProductService productService;
	
	Product product;
	
	Category category;
	
	List<Product> productlist;
	
	private final String productId = "786";
	
	private final String userId="user1";
	
	private final String producttype= "premium jewellery";
	
	private final String productName="Tanishq Gold Neck Chain";
	
	private final String categoryName="Women Fashion";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		product = new Product();
		
		category = new Category("category1","Women Fashion","ImgUrl");
		
		List<String> image = new ArrayList<>();
		image.add("url1");
		
		product.setProducttype("premium jewellery");
		product.setProductName("Tanishq Gold Neck Chain");
		product.setCategory(category);
		product.setDescription("Made by 24 carat gold");
		product.setPrice(50000);
		product.setImage(image);
		productlist = new ArrayList<Product>();
		productlist.add(product);
		
		
	}
	
	@Test
	@Order(1)
	void addProduct() {
		// first check that all the required parameters are in the product class
		assertNotNull(product, "product is null");
		
	}
	
	@Test
	@Order(2)
	void addProductNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(product, "product is null");
	}
	
	@Test
	@Order(3)
	void addProductProductTypeNotNull() {
		assertNotNull(product.getProductType(), "Product type is required");
	}
	
	@Test
	@Order(4)
	void addProductProductNameNotNull() {
		assertNotNull(product.getProductName(), "Product Name is required");
	}
	
	
	@Test
	@Order(5)
	void addProductCategoryNotNull() {
		assertNotNull(product.getCategory(), "product category is required");
	}
	
	
	@Test
	@Order(6)
	void addProductDescriptionNotNull() {
		assertNotNull(product.getDescription(), "product description is required");
	}
	
	@Test
	@Order(7)
	void addProductPriceNotNull() {
		assertNotNull(product.getPrice(), "product price is required");
	}
	
	@Test
	@Order(8)
	void addProductImageNotNull() {
		assertNotNull(product.getImage(), "Product image is required");
	}
	
	@Test
	@Order(9)
	void getAllProducts() {
		
		when(productService.getAllProducts()).thenReturn(productlist);
		
		assertEquals(1,productService.getAllProducts().size() );
	}
	
	@Test
	@Order(10)
	void addProductCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertNotNull(productRest);
	}
	
	@Test
	@Order(11)
	void addProductProductNameCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getProductName(), productRest.getProductName());
	}
	
	@Test
	@Order(12)
	void addProductProductCategoryCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getCategory().getCategoryName(), productRest.getCategory().getCategoryName());
	}
	
	@Test
	@Order(13)
	void addProductProductTypeCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getProductType(), productRest.getProductType());
	}
	
	
	@Test
	@Order(14)
	void addProductProductDescriptionCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getDescription(), productRest.getDescription());
	}
	
	
	
	@Test
	@Order(15)
	void addProductProductPriceCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getPrice(), productRest.getPrice());
	}
	
	@Test
	@Order(16)
	void addProductProductImageCheck() {
		when(productService.addProduct(product, userId)).thenReturn(product);
		
		Product productRest = productServiceController.addProduct(product, userId);
		
		assertEquals(product.getImage().size(), productRest.getImage().size());
	}
	
	@Test
	@Order(17)
	void getProductById() {
		when(productService.getProductById(productId)).thenReturn(product);
		
		Product productRest=productServiceController.getProductById(productId);
		
		assertNotNull(productRest, "Product not avalable in db");
		
		assertEquals(product.getProductid(), productRest.getProductid());
		
		
		
	}
	
	@Test
	@Order(18)
	void getProductByType() {
		when(productService.getProductByType(producttype)).thenReturn(productlist);
		
		productlist = productServiceController.getProductByType(producttype);
		
		assertNotNull(product);
		
		assertEquals(product.getProductType(), "premium jewellery");
		
		
	}
	
	@Test
	@Order(19)
	void getProductByName() {
		when(productService.getProductByName(productName)).thenReturn(productlist);
		
		productlist= productServiceController.getProductByName(productName);
		
		assertNotNull(product);
		
		assertEquals(product.getProductName(),"Tanishq Gold Neck Chain");
	}
	
	
	@Test
	@Order(20)
	void getProductByCategory() {
		
		when(productService.getProductByCategory(categoryName)).thenReturn(productlist);
		
		productlist= productServiceController.getProductByCategory(categoryName);
		
		assertNotNull(product);
		
		assertEquals(product.getCategory().getCategoryName(),"Women Fashion");
	}
	

	@Test
	@Order(21)
	void deleteProductById() {
		
		when(productService.deleteProductById(productId)).thenReturn("Deleted Succesfully ");
		
		when(productService.getProductById(productId)).thenReturn(product);
		
		String delete = productServiceController.deleteProductById(productId);
		
		assertEquals("Deleted Succesfully ", delete ,"Product not avalable in db");
		
	}
	
}
