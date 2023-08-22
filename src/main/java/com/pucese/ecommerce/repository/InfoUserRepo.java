package com.pucese.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucese.ecommerce.model.InformationUser;
import com.pucese.ecommerce.model.User;

public interface InfoUserRepo  extends JpaRepository<InformationUser, Integer>{

}
