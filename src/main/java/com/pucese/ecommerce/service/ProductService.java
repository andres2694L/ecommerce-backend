package com.pucese.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pucese.ecommerce.dto.ProductDTO;
import com.pucese.ecommerce.exceptions.ProductNotExistsException;
import com.pucese.ecommerce.model.Category;
import com.pucese.ecommerce.model.Product;
import com.pucese.ecommerce.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;

	public void createProduct(ProductDTO productDTO, Category category) {
		
		Product product = new Product();
		product.setDescription(productDTO.getDescription());
		product.setImageUrl(productDTO.getImageUrl());
		product.setName(productDTO.getName());
		product.setCategory(category);
		product.setPrice(productDTO.getPrice());
		productRepo.save(product);
		
	}

	public ProductDTO getProductDTO(Product product) {
		ProductDTO productdto = new ProductDTO();
		productdto.setDescription(product.getDescription());
		productdto.setImageUrl(product.getImageUrl());
		productdto.setName(product.getName());
		productdto.setCategory_id(product.getCategory().getId());
		productdto.setPrice(product.getPrice());
		productdto.setId(product.getId());
		return productdto;
	}
	
	public List<ProductDTO> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		List<ProductDTO> productDTOS = new ArrayList<>();
		for(Product product: allProducts) {
			productDTOS.add(getProductDTO(product));
		}
		return productDTOS;
	}

	public void updateProduct(ProductDTO productDTO, Integer productId) throws Exception {
		Optional<Product> optionalProduct = productRepo.findById(productId);
		if(!optionalProduct.isPresent()) {
			throw new Exception("product not present");
		}
		Product product = optionalProduct.get();
		product.setDescription(productDTO.getDescription());
		product.setImageUrl(productDTO.getImageUrl());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		productRepo.save(product);
	}
	
	public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }

}
