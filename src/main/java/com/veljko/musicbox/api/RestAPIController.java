package com.veljko.musicbox.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veljko.musicbox.service.AuthorizationService;

@RestController
@RequestMapping("api/v1")
public class RestAPIController {

	@Autowired
	@Qualifier("spotifyAuthorizationService")
	private AuthorizationService authorizationService;
	
	@GetMapping("test")
	public String test () {
		return authorizationService.test();
	}
	
}
