package com.pucese.ecommerce.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pucese.ecommerce.dto.ResponseDTO;
import com.pucese.ecommerce.dto.user.SignInDTO;
import com.pucese.ecommerce.dto.user.SignInResponseDTO;
import com.pucese.ecommerce.dto.user.SignupDTO;
import com.pucese.ecommerce.exception.BadRequestException;
import com.pucese.ecommerce.exception.ResourceNotFoundException;
import com.pucese.ecommerce.exceptions.AuthenticationFailException;
import com.pucese.ecommerce.model.AuthProvider;
import com.pucese.ecommerce.model.AuthenticationToken;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.payload.ApiResponse;
import com.pucese.ecommerce.payload.AuthResponse;
import com.pucese.ecommerce.payload.LoginRequest;
import com.pucese.ecommerce.payload.SignUpRequest;
import com.pucese.ecommerce.repository.TokenRepo;
import com.pucese.ecommerce.repository.UserRepository;
import com.pucese.ecommerce.security.CurrentUser;
import com.pucese.ecommerce.security.TokenProvider;
import com.pucese.ecommerce.service.AuthenticationService;
import com.pucese.ecommerce.service.UserService;

@RequestMapping("auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private TokenProvider tokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	//user me



    // signup

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


    // signin

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
	
}
