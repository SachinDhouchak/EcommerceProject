package com.ecomsb.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.dto.ProductDTO;
import com.ecomsb.model.Category;
import com.ecomsb.model.Product;
import com.ecomsb.service.CategoryService;
import com.ecomsb.service.ProductService;


@RestController
public class AdminController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";   // icons like that add,delete,update
	}
	
	@GetMapping("/admin/categories")
	public ResponseEntity<?> getCat() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("categories", categoryService.getAllCategory() );
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/admin/categories/add")
	public ResponseEntity<?> getCatAdd() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("category", new Category());
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/admin/categories/add")
	public String posCatAdd(@ModelAttribute("category") Category category) {       // category exact this name should be in frontend
		  categoryService.addCategory(category);    
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
	@PostMapping("/admin/categories/update/{id}")
	public ResponseEntity<?> updateCat(@PathVariable int id) {
		
		Optional<Category> category = categoryService.getCategoryById(id);
		
		Map<String, Object> response = new HashMap<String, Object>();
		  if (category.isPresent()) {
			  response.put("category", category.get());
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)  ;
		}			
	}
	
	
	
	
	
	// ---------------        PRODUCT SECTION      ------------------------------
	
	
	@Autowired
	ProductService productService;
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	
	@GetMapping("/admin/products")
	public ResponseEntity<?> products() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("products", productService.getAllProduct() );
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/admin/products/add")
	public ResponseEntity<?> productAddGet() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("productDTO", new ProductDTO() );
		response.put("categories", categoryService.getAllCategory() );
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
			   @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName  ) throws IOException {
		
		Product product = new Product();		
		product.setId(productDTO.getId());		
		product.setName(productDTO.getName() );		
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());		
		product.setPrice(productDTO.getPrice());		
		product.setWeight(productDTO.getWeight());		
		product.setDescription(productDTO.getDescription());
		
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes() );
		}else {
			imageUUID = imgName;
		}
		
		product.setImageName(imageUUID);
		
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
			   
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {		
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/admin/product/update/{id}")
	public ResponseEntity<?> updateProductGet(@PathVariable long id) {
		
		Product product = productService.getProductById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("categories", categoryService.getAllCategory() );
		response.put("productDTO", productDTO );		
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
}
