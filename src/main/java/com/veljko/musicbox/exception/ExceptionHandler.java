package com.veljko.musicbox.exception;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class, Exception.class})
	public ResponseEntity<String> handleException (Exception ex) {
		LOGGER.error("General exception occured. Exception: {} Message: {}", ex.getClass(), ex.getMessage());
		
		JSONObject response = new JSONObject();
		response.put("exception", ex.getClass());
		response.put("message", ex.getMessage());
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
