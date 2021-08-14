package com.spring.sample.ppm.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationError {

	public ResponseEntity<Object> mapValidationService(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError err : result.getFieldErrors()) {
				errors.put(err.getField(), err.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
