package com.pucese.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pucese.ecommerce.dto.ResponseDTO;
import com.pucese.ecommerce.dto.company.CompanySignUpDTO;
import com.pucese.ecommerce.dto.company.CompnaySignInDTO;
import com.pucese.ecommerce.exceptions.CustomException;
import com.pucese.ecommerce.model.AuthenticationToken;
import com.pucese.ecommerce.repository.CompanyUserRepo;
import com.pucese.ecommerce.model.CompanyUser;

@Service
public class CompanyUserService {
	
	@Autowired
	CompanyUserRepo companyRepo;
	
	@Autowired
    AuthenticationService authenticationService;
	
	@Autowired
	RestTemplate restemplate = new RestTemplate();
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	private final String confirmRuc = "https://api-sbox.veronica.ec/api/v1.0/usuarios/me";
	
	
	
	public ResponseDTO signUp(CompanySignUpDTO companysignUp) {
		
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 /*************new Object*******/
		
		
		 if (Objects.nonNull(companyRepo.findByEmail(companysignUp.getEmail()))) {
	            // we have an user
	            throw new CustomException("Company already present");
	     }
		 
		 String encryptedpassword = companysignUp.getPassword();

	        try {
	            encryptedpassword = hashPassword(companysignUp.getPassword());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        CompanyUser companyUser = new CompanyUser(companysignUp.getEmail(), encryptedpassword);

	        companyRepo.save(companyUser);

	        // save the user

	        // create the token

	        final AuthenticationToken authenticationToken = new AuthenticationToken(companyUser);

	        authenticationService.saveConfirmationToken(authenticationToken);

	        ResponseDTO responseDto = new ResponseDTO("success", "Company created succesfully");
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

}
