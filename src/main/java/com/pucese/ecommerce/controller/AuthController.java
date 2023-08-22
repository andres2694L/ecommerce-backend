package com.pucese.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pucese.ecommerce.exception.BadRequestException;
import com.pucese.ecommerce.model.AuthProvider;
import com.pucese.ecommerce.model.CompanyUser;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.payload.ApiResponse;
import com.pucese.ecommerce.payload.AuthResponse;
import com.pucese.ecommerce.payload.CompanySignUpRequest;
import com.pucese.ecommerce.payload.LoginRequest;
import com.pucese.ecommerce.payload.SignUpRequest;
import com.pucese.ecommerce.repository.CompanyUserRepo;
import com.pucese.ecommerce.repository.UserRepository;
import com.pucese.ecommerce.security.TokenProvider;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private CompanyUserRepo compaRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setFirstName(signUpRequest.getFirstname());
        user.setLastName(signUpRequest.getLastname());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
    
    @PostMapping("/companySignup")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanySignUpRequest compRequest){
    	
    	if(compaRepo.existsByEmail(compRequest.getEmail())) {
    		throw new BadRequestException("Email already in use");
    	}
    	CompanyUser comp = new CompanyUser();
    	comp.setEmail(compRequest.getEmail());
    	comp.setPassword(compRequest.getPassword());
    	comp.setProvider(AuthProvider.local);
    	comp.setPassword(passwordEncoder.encode(comp.getPassword()));
    	
    	CompanyUser result = compaRepo.save(comp);
    	
    	 URI location = ServletUriComponentsBuilder
                 .fromCurrentContextPath().path("/user/me")
                 .buildAndExpand(result.getId()).toUri();
    	
    	return ResponseEntity.created(location)
    			.body(new ApiResponse(true, "Company registered succesfully@"));
    }

}
