package com.pucese.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucese.ecommerce.dto.ResponseDTO;
import com.pucese.ecommerce.dto.user.SignInDTO;
import com.pucese.ecommerce.dto.user.SignInResponseDTO;
import com.pucese.ecommerce.dto.user.SignupDTO;
import com.pucese.ecommerce.exceptions.AuthenticationFailException;
import com.pucese.ecommerce.exceptions.CustomException;
import com.pucese.ecommerce.model.AuthenticationToken;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.repository.UserRepo;

@Service
public class UserService {
	  
		@Autowired
	    UserRepo userRepository;

	    @Autowired
	    AuthenticationService authenticationService;

	    @Transactional
	    public ResponseDTO signUp(SignupDTO signupDto) {
	        // check if user is already present
	        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
	            // we have an user
	            throw new CustomException("user already present");
	        }


	        // hash the password

	        String encryptedpassword = signupDto.getPassword();

	        try {
	            encryptedpassword = hashPassword(signupDto.getPassword());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
	                signupDto.getEmail(), encryptedpassword);

	        userRepository.save(user);

	        // save the user

	        // create the token

	        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

	        authenticationService.saveConfirmationToken(authenticationToken);

	        ResponseDTO responseDto = new ResponseDTO("success", "user created succesfully");
	        return responseDto;
	    }

	    private String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	        byte[] digest = md.digest();
	        String hash = DatatypeConverter
	                .printHexBinary(digest).toUpperCase();
	        return hash;
	    }

	    public SignInResponseDTO signIn(SignInDTO signInDto) {
	        // find user by email

	        User user = userRepository.findByEmail(signInDto.getEmail());

	        if (Objects.isNull(user)) {
	            throw new AuthenticationFailException("user is not valid");
	        }

	        // hash the password

	        try {
	            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
	                throw new AuthenticationFailException("wrong password");
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        // compare the password in DB

	        // if password match

	        AuthenticationToken token = authenticationService.getToken(user);

	        // retrive the token

	        if (Objects.isNull(token)) {
	            throw new CustomException("token is not present");
	        }

	        return new SignInResponseDTO("sucess", token.getToken());

	        // return response
	    }
}
