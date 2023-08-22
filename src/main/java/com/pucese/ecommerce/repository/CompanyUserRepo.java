package com.pucese.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucese.ecommerce.model.CompanyUser;
import com.pucese.ecommerce.model.User;

@Repository
public interface CompanyUserRepo extends JpaRepository<CompanyUser, Long> {
	
	CompanyUser findByEmail(String email);
	
	 Boolean existsByEmail(String email);

}
