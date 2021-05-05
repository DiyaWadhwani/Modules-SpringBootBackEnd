package com.wolken.wolkenapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

	public ResourceNotFoundException(String message) {
		super(message);
		logger.warn("ResNotFoundExc thrown !!");
	}
}
