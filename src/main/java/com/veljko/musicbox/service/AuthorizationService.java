package com.veljko.musicbox.service;


import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.spotify.SpotifyAPIEndpoints;

@Service("spotifyAuthorizationService")
public class AuthorizationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
	
	private static final String AUTH_BODY_PARAMETER = "grant_type";
	private static final String AUTH_BODY_VALUE = "client_credentials";
	private static final String BASIC_AUTH_DELIMITER = ":";
	
	@Value("${spotify.client_id}")
	private String spotifyClientId;
	@Value("${spotify.client_secret}")
	private String spotifyClientSecret;
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	public AuthorizationResponseModel authorize () {
		LOGGER.info("Authorizing client {} against Spotify Authorization API", spotifyClientId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth(getBasicAuthEncoded());
		
		MultiValueMap<String, String> body= new LinkedMultiValueMap<>();
		body.add(AUTH_BODY_PARAMETER, AUTH_BODY_VALUE);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		
		return restTemplate.exchange(getAuhtorizationEndpoint(), HttpMethod.POST, requestEntity, AuthorizationResponseModel.class).getBody();
	}
	
	private String getBasicAuthEncoded () {
		StringBuilder builder = new StringBuilder();
		
		String basicAuthString = builder.append(spotifyClientId)
				.append(BASIC_AUTH_DELIMITER)
				.append(spotifyClientSecret)
				.toString();
		
		return Base64.getEncoder().encodeToString(basicAuthString.getBytes());
	}
	
	private String getAuhtorizationEndpoint () {
		StringBuilder builder = new StringBuilder();
		
		return builder.append(SpotifyAPIEndpoints.BASE_PATH)
						.append(SpotifyAPIEndpoints.AUTHORIZATION_ENDPOINT)
						.toString();
	}
	
}
