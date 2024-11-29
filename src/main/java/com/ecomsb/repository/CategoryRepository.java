package com.ecomsb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomsb.model.Category;

public interface CategoryRepository extends JpaRepository< Category, Integer> {

	public List<Category> findByNameContaining(String name);
	
	
	
}
