package io.javabrains.cart;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class CartApplication1 {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication1.class, args);

	}

	@Bean
	public Docket swaggerConfiguration() {
		
		//return a prepared docket instance
		return new Docket(DocumentationType.SWAGGER_2)
							.select()
							.paths(PathSelectors.ant("/cart/**"))
							.apis(RequestHandlerSelectors.basePackage("io.javabrains"))
							.build()
							.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("Cart Service Api",
							"Sample API", 
							"1.0", 
							"Free to use", 
							new Contact("ABC", "https://abc.com", "abc mail"),
							"API-licence"
							,"licence url for the app"
							,Collections.emptyList());
		
		
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getrestTemplate(){
		return new RestTemplate();
	}
}
