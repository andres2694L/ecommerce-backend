package com.pucese.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucese.ecommerce.exception.ResourceNotFoundException;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.repository.UserRepository;
import com.pucese.ecommerce.security.CurrentUser;
import com.pucese.ecommerce.security.UserPrincipal;

@RestController
public class UserAuth {
	
	 @Autowired
	    private UserRepository userRepository;

	 	@GetMapping("/user/me")
	    @PreAuthorize("hasRole('USER')")
	    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
	        return userRepository.findById(userPrincipal.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	    }

}
