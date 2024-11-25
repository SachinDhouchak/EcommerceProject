package com.ecomsb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecomsb.global.GlobalData;
import com.ecomsb.model.Category;
import com.ecomsb.model.Product;
import com.ecomsb.service.CategoryService;
import com.ecomsb.service.ProductService;



@RestController
public class HomeController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public ResponseEntity<?> home() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("cartCount",GlobalData.cart.size() );
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/shop") 
	public ResponseEntity<?> shop() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("categories", categoryService.getAllCategory() );		
		response.put("products", productService.getAllProduct() );		
		response.put("cartCount",GlobalData.cart.size());
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/shop/category/{id}")
    public ResponseEntity<?> shopByCategory(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categoryService.getAllCategory());
        response.put("products", productService.getAllProductsByCategoryId(id));
        response.put("cartCount", GlobalData.cart.size());

        return ResponseEntity.ok(response);
    }
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public ResponseEntity<?> viewProduct(@PathVariable int id) {
		Map<String, Object> response = new HashMap<String, Object>();				
		response.put("product", productService.getProductById(id).get() );    // if get() is not there what happen   
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
}
