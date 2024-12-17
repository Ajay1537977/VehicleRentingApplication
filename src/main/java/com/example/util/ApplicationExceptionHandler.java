package com.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.FailedToUploadException;
import com.example.exception.UserNotFoundException;


@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleUserNotFound(UserNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStructure.create(HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), "User Not found By the given Id"));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleFailedToUpload(FailedToUploadException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStructure.create(HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), "Failed to upload the Image"));
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleFailedToUpload(UsernameNotFoundException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStructure.create(HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), "user is not there"));
		
	}

}
