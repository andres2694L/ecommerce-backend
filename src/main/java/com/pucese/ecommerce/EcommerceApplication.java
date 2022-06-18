package com.pucese.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pucese.ecommerce.config.AppProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.pucese.ecommerce.*")
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
