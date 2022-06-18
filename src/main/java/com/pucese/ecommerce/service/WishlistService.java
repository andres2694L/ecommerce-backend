package com.pucese.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pucese.ecommerce.dto.ProductDTO;
import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.model.Wishlist;
import com.pucese.ecommerce.repository.WishlistRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
	 	
		@Autowired
	    WishlistRepo wishListRepository;

	    @Autowired
	    ProductService productService;

	    public void createWishlist(Wishlist wishList) {
	        wishListRepository.save(wishList);
	    }

	    public List<ProductDTO> getWishListForUser(User user) {
	        final List<Wishlist> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
	        List<ProductDTO> productDtos = new ArrayList<>();
	        for (Wishlist wishList: wishLists) {
	            productDtos.add(productService.getProductDTO(wishList.getProduct()));
	        }

	        return productDtos;
	    }
}
