package com.pucese.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "companyuser", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class CompanyUser {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 
	 @Email
	 @Column(nullable = false)
	 private String email;
	 
	 @Column(nullable = false)
	 private Boolean emailVerified = false;
	 
	 @NotNull
	 @Enumerated(EnumType.STRING)
	 private AuthProvider provider;
	 
	 private String providerId;
	 
	
	 
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CompanyUser() {
		
	}



	public CompanyUser(String email,
			String password) {
		this.email = email;
		this.password = password;
	}



	@JsonIgnore
	private String password;
	 

}
