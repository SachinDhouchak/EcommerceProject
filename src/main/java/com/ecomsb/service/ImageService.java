package com.ecomsb.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.model.Image;
import com.ecomsb.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public String addImage(MultipartFile file) {
		Image image = new Image();

		image.setImageName(file.getOriginalFilename());
		image.setImageType(file.getContentType());
		try {
			image.setImageData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed";
		}

		imageRepository.save(image);
		return "Success";
	}
	
	
	public Image getImageById(Long id) {
		return imageRepository.findById(id).orElseThrow( () -> new RuntimeException("Image not found") );
		 
	}

}
