package com.ecomsb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;








import com.ecomsb.model.Category;
import com.ecomsb.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> getAllCategory(Integer pageNumber,Integer pageSize )  { 
		
		Pageable p = PageRequest.of(pageNumber-1,pageSize );
		Page<Category> pageData =  categoryRepository.findAll(p);
		System.out.println(pageData);
		List<Category> resultList = pageData.getContent();
		return resultList; 
		}
	
	public void addCategory(Category category) { categoryRepository.save(category);	}
	
	public void removeCategoryById(int id) { categoryRepository.deleteById(id); }
	
	public Optional<Category> getCategoryById(int id) { return categoryRepository.findById(id); }
	
}
