package com.pucese.ecommerce.controller;

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
import com.pucese.ecommerce.model.Category;
import com.pucese.ecommerce.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "the category was created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }
    
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
    	if(categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "category not found"), HttpStatus.NOT_FOUND);
    	}
    	categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "the category was edited"), HttpStatus.OK);
    }
    
    
}
