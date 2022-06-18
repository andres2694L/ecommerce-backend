package com.pucese.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucese.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
