package com.pucese.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucese.ecommerce.model.AuthenticationToken;
import com.pucese.ecommerce.model.User;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
	
	AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);

}
