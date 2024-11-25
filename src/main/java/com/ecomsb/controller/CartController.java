package com.ecomsb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomsb.global.GlobalData;
import com.ecomsb.model.Product;
import com.ecomsb.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {		
		  GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}	
	
	@GetMapping("/cart")
	public ResponseEntity<?> cartGet() {	
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("cartCount",GlobalData.cart.size());
		response.put("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum() );
		response.put("cart",GlobalData.cart);	
			
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		   GlobalData.cart.remove(index);		
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<?> checkout(Model model) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum()  );
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	
	
	
}
