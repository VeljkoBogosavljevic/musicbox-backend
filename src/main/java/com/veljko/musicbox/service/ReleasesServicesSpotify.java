package com.veljko.musicbox.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.model.ReleasesResponseModel;
import com.veljko.musicbox.spotify.SpotifyAPIEndpoints;

@Service("spotifyReleasesService")
public class ReleasesServicesSpotify implements IReleasesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReleasesServicesSpotify.class);
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Override
	public ReleasesResponseModel fetchNewReleases (String market) {
		LOGGER.info("Fetching new releases against spotify for market {}", market);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		AuthorizationResponseModel authorizationResponseModel = authorizationService.authorize();
		
		String authorizationHeaderValue = new StringBuilder()
													.append(authorizationResponseModel.getTokenType())
													.append(" ")
													.append(authorizationResponseModel.getAccessToken())
													.toString();
		
		headers.set("Authorization", authorizationHeaderValue);
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		try {
			ResponseEntity<ReleasesResponseModel> response = restTemplate.exchange(getNewReleasesEndpoint(), HttpMethod.GET, requestEntity, ReleasesResponseModel.class, market);
			
			LOGGER.info("Fetching new releases against spotify succesful with status {}", response.getStatusCode());
			return response.getBody();
			
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Fetching new releases against spotify failed due to HttpClientErrorException. HTTP Response code: {} Message: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
			throw new RuntimeException(ex);
		} catch (Exception ex) {
			LOGGER.error("Fetching new releases against spotify failed due to general Exception. Message: {}", ex.getMessage());
			throw ex;
		}
	}
	
	private String getNewReleasesEndpoint () {
		StringBuilder builder = new StringBuilder();
		
		return builder.append(SpotifyAPIEndpoints.BASE_PATH)
						.append(SpotifyAPIEndpoints.NEW_RELEASES_ENDPOINT)
						.append("?country={market}")
						.toString();
	}

}
