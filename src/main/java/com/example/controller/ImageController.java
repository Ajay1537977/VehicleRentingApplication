package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.User;
import com.example.service.ImageService;
import com.example.util.SimpleResponseStructure;

@RestController
public class ImageController {
	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}
	@PostMapping("/upload-profile")
	public ResponseEntity<SimpleResponseStructure> uploadProfile(@RequestParam ("userId") int userId,
				@RequestParam("file") MultipartFile file ){
		
		imageService.uploadUserProfilePicture(userId,file);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SimpleResponseStructure.create(HttpStatus.CREATED.value(), "Profile Picture Updated"));
	}
	@GetMapping("/fetch-image")
	public ResponseEntity<byte[]> fetchImage(@RequestParam("imageId") int imageId)
	{
		Image image=imageService.fetching(imageId);
	byte[] imageByte=image.getImageBytes();
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf(image.getContentType()))
				.body(imageByte);
	}


}
