package com.veljko.musicbox.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("spotifyAuthorizationService")
public class AuthorizationService {

	@Value("${spotify.client_id}")
	private String spotifyClientId;
	@Value("${spotify.client_secret}")
	private String spotifyClientSecret;
	
	public String test () {
		return spotifyClientId + " " + spotifyClientSecret;
	}
	
}
