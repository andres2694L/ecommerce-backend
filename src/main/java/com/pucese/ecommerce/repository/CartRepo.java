package com.pucese.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucese.ecommerce.model.Cart;
import com.pucese.ecommerce.model.User;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
