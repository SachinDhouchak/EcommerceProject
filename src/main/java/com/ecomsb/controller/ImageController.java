package com.ecomsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomsb.service.DocumentService;

@RestController
public class ImageController {
	
	@Autowired
	DocumentService documentService;
	

	@PostMapping("/upload")
	public ResponseEntity<String> saveImage(@RequestParam("doc") MultipartFile file )  {
		
		documentService.saveImage(file);
		return ResponseEntity.ok().body("Image are uploaded");
	}
	
}
