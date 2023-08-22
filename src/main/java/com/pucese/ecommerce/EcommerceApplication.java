package com.pucese.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.pucese.ecommerce.config.AppProperties;
import com.pucese.ecommerce.model.Category;
import com.pucese.ecommerce.service.CategoryService;




@SpringBootApplication(scanBasePackages= {"com.pucese.ecommerce","com.pucese.application"})
@Configuration
@EnableConfigurationProperties(AppProperties.class)
@EnableAutoConfiguration
@ComponentScan(basePackages="com.pucese.ecommerce.*")
public class EcommerceApplication {
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(EcommerceApplication.class, args);
		
		
	}
	
}
