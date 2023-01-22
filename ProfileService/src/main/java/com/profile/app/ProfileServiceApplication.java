package com.profile.app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class ProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	
	@Bean
	public Docket swaggerConfiguration() {
		
		//return a prepared docket instance
		return new Docket(DocumentationType.SWAGGER_2)
							.select()
							.paths(PathSelectors.ant("/user/**"))
							.apis(RequestHandlerSelectors.basePackage("com.profile"))
							.build()
							.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("Profile Service Api",
							"Sample API", 
							"1.0", 
							"Free to use", 
							new Contact("ABC", "https://abc.com", "abc mail"),
							"API-licence"
							,"licence url for the app"
							,Collections.emptyList());
		
		
	}
}
