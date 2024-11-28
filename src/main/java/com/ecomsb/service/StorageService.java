package com.ecomsb.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.model.ImageData;
import com.ecomsb.repository.StorageRepository;

@Service
public class StorageService {

	@Autowired
	private StorageRepository storageRepository;	
	
	public String uploadImage(MultipartFile file) throws IOException   {
		ImageData imageData = new ImageData();
		
		imageData.setName(file.getOriginalFilename());
		imageData.setType(file.getContentType());
		imageData.setImageData(file.getBytes());	
		
		
		storageRepository.save(imageData);
					
		return "sachin";
	}
	
	
}
