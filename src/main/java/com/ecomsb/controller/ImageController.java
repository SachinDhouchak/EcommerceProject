package com.ecomsb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.service.StorageService;

@RestController
public class ImageController {

	@Autowired
	private StorageService storageService;

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
