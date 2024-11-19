package com.ecomsb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecomsb.repository.CategoryRepository;

@SpringBootApplication
public class EcomApplication {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

	

}
