package com.ecomsb;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecomsb.model.Category;
import com.ecomsb.model.Product;
import com.ecomsb.model.Role;
import com.ecomsb.model.User;
import com.ecomsb.repository.CategoryRepository;
import com.ecomsb.repository.ProductRepository;
import com.ecomsb.repository.RoleRepository;
import com.ecomsb.repository.UserRepository;

@SpringBootApplication
public class EcomApplication  {
	
	

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}
	
	@Autowired
	ProductRepository productRepository;
	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		Category category =	new categoryRepository.findById(1);
//		
//		Product product= new Product();
//		product.setDescription("This is fresh banana");
//		product.setName("Banana");
//		product.setPrice(80);
//		product.setWeight(1000);
//		product.setImageName("apple.png");
//		product.setCategory(category);		
//		productRepository.save(product);
//		
//		
//	}



	

}
