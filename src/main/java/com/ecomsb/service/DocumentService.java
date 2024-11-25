package com.ecomsb.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.model.Document;
import com.ecomsb.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	DocumentRepository documentRepository;
	
	public String saveImage(MultipartFile file) {
	
		
		
		try {
			Document document = new Document();
			document.setName(file.getOriginalFilename());
			document.setType(file.getContentType());
			document.setContent(file.getBytes());
			documentRepository.save(document);
			
		} catch (Exception e) {
			throw new RuntimeException("Image is not uploaded" + e);
		}
		
		
		return "";
	}
	
}
