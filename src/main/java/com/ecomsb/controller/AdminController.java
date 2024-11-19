package com.ecomsb.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.dto.ProductDTO;
import com.ecomsb.model.Category;
import com.ecomsb.model.Product;
import com.ecomsb.service.CategoryService;
import com.ecomsb.service.ProductService;

@ResponseBody
@Controller
public class AdminController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";   // icons like that add,delete,update
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory() );
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());      // category name is from backend and backend name should be category exact 
		return "categoriesAdd";
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
	public String updateCat(@PathVariable int id,Model model) {
		
		Optional<Category> category = categoryService.getCategoryById(id);
		  if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}			
	}
	
	
	
	
	
	// ---------------        PRODUCT SECTION      ------------------------------
	
	
	@Autowired
	ProductService productService;
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct() );
		return "products";
	}
	
	
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO() );
		model.addAttribute("categories", categoryService.getAllCategory() );
		return "productsAdd";
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
	public String updateProductGet(@PathVariable long id, Model model) {
		
		Product product = productService.getProductById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());  
		model.addAttribute("categories", categoryService.getAllCategory() );
		model.addAttribute("productDTO", productDTO );
		
		
		return "productsAdd";
	}
	
	
	
	
	
}
