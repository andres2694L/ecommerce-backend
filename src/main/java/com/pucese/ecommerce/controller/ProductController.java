package com.pucese.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucese.ecommerce.common.ApiResponse;
import com.pucese.ecommerce.dto.ProductDTO;
import com.pucese.ecommerce.model.Category;
import com.pucese.ecommerce.model.Product;
import com.pucese.ecommerce.repository.CategoryRepo;
import com.pucese.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
		Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategory_id());
        if(!optionalCategory.isPresent()) {
    		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"), HttpStatus.BAD_REQUEST); 
        }
        productService.createProduct(productDTO, optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "the product was added"), HttpStatus.CREATED); 
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductDTO>> getProducts(){
		List<ProductDTO> allProducts = productService.getAllProducts();
		return new ResponseEntity<>(allProducts, HttpStatus.OK); 
	}
	
	@PostMapping("/updateProduct/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDTO productDTO) throws Exception{
		Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategory_id());
        if(!optionalCategory.isPresent()) {
    		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"), HttpStatus.BAD_REQUEST); 
        }
        productService.updateProduct(productDTO, productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "the product was added"), HttpStatus.CREATED); 
	}
	

}
