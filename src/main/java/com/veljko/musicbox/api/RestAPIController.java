package com.veljko.musicbox.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.service.IAuthorizationService;

@RestController
@RequestMapping("api/v1")
public class RestAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestAPIController.class);
	
	@Value("${rest.authorization.api_key}")
	private String authorizationApiKey;

	@Autowired
	@Qualifier("cachedAuthorizationService")
	private IAuthorizationService authorizationService;
	
	@PostMapping(value = "/authorize", headers = "api_key", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorizationResponseModel> authorize (@RequestHeader(value = "api_key") String apiKey) {
		LOGGER.info("Authorize API endpoint");
		
		if (!isApiKeyValid(apiKey)) {
			LOGGER.warn("Api Key {} is not valid for the Authorize API endpoint", apiKey);
			return new ResponseEntity<AuthorizationResponseModel>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<AuthorizationResponseModel>(authorizationService.authorize(), HttpStatus.OK);	
	}
	
	private boolean isApiKeyValid (String apiKey) {
		if (apiKey == null || !apiKey.equals(authorizationApiKey)) {
			return false;
		}
		return true;
	}
	
}
