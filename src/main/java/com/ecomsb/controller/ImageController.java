package com.ecomsb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.model.Image;
import com.ecomsb.service.ImageService;
import com.ecomsb.service.StorageService;


@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private StorageService storageService;

	@PostMapping("/addImage")
	public ResponseEntity<String> addImage(
			@RequestParam("image") MultipartFile file) {

		if (file.isEmpty()) {
			return ResponseEntity.status(400).body("No file uploaded");
		}

		if (imageService.addImage(file).equals("Success")) {
			return ResponseEntity.ok().body("Image uploaded successfully");
		} else {
			return ResponseEntity.status(500).body("Failed to upload file");
		}
	}
	
	
	@GetMapping("/getImage/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id )  {
		
		Image image = imageService.getImageById(id);
		
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getImageType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getImageName() + "\"")
                .body(image.getImageData());
		          
	}
	
	@PostMapping("/addImage")
	public ResponseEntity<String> uploadImage(
			@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			System.out.println("No file is uploaded");
			return ResponseEntity.status(400).body("No file uploaded");
		}
		try {
			String result = storageService.uploadImage(file);
			System.out.println("file uploaded successfully");
			return ResponseEntity.ok(result);
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Failed to upload image");
		}
	}

}
