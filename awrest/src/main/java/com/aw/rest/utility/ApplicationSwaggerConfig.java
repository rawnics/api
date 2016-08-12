package com.aw.rest.utility;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationSwaggerConfig {
	

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
			"Awnics REST APIs", 				//Title
			"Description of your API",			//Description
			"1.0",								//Version
			"Terms Of Service",					//TOS
			new Contact("AWNICS", 				//Contact
						"www.awnics.com", 
						"info@awnics.com"),
			"API License",						//License
			"API License URL" 					//License Url
			);
		return apiInfo;
	}

}
