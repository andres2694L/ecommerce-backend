package com.pucese.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucese.ecommerce.dto.ResponseDTO;
import com.pucese.ecommerce.dto.user.SignInDTO;
import com.pucese.ecommerce.dto.user.SignInResponseDTO;
import com.pucese.ecommerce.dto.user.SignupDTO;
import com.pucese.ecommerce.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
	
	@Autowired
    UserService userService;

    // two apis

    // signup

    @PostMapping("/signup")
    public ResponseDTO signup(@RequestBody SignupDTO signupDto) {
        return userService.signUp(signupDto);
    }


    // signin

    @PostMapping("/signin")
    public SignInResponseDTO signIn(@RequestBody SignInDTO signInDto) {
        return userService.signIn(signInDto);
    }
	
}
