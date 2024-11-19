package com.ecomsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomsb.model.Category;

public interface CategoryRepository extends JpaRepository< Category, Integer> {

}
