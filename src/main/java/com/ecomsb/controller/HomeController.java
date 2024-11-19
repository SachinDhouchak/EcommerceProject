package com.ecomsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomsb.global.GlobalData;
import com.ecomsb.model.Category;
import com.ecomsb.model.Product;
import com.ecomsb.service.CategoryService;
import com.ecomsb.service.ProductService;



@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop") 
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct() );
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id,Model model) {
		
		model.addAttribute("categories", categoryService.getAllCategory() );		
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id,Model model) {
		
		model.addAttribute("product", productService.getProductById(id).get() );	// if get() is not there what happen	
		return "viewProduct";
	}
	
	
	
	
	
	
	
}
