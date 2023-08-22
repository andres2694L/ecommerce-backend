package com.pucese.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucese.ecommerce.exception.ResourceNotFoundException;
import com.pucese.ecommerce.model.InformationUser;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.repository.UserRepository;
import com.pucese.ecommerce.security.CurrentUser;
import com.pucese.ecommerce.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getFirstname()));
    }
    
    @PostMapping("/user/addinformation")
    public InformationUser addInformationUser(@RequestBody InformationUser userInfor) {
    	return null;
    }
}
