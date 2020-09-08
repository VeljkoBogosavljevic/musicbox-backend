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

import com.veljko.musicbox.model.Album;
import com.veljko.musicbox.model.AuthorizationResponseModel;
import com.veljko.musicbox.model.TracksResponseModel;
import com.veljko.musicbox.spotify.SpotifyAPIEndpoints;

@Service("spotifyAlbumService")
public class AlbumServiceSpotify implements IAlbumService {

private static final Logger LOGGER = LoggerFactory.getLogger(AlbumServiceSpotify.class);
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Override
	public Album fetchAlbum (String id, String market) {
		LOGGER.info("Fetching album with id {} against spotify for market {}", id, market);
		
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
			ResponseEntity<Album> response = restTemplate.exchange(getAlbumEndpoint(), HttpMethod.GET, requestEntity, Album.class, id, market);
			
			LOGGER.info("Fetching album with id {} against spotify succesful with status {}", id, response.getStatusCode());
			return response.getBody();
			
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Fetching album with id {} against spotify failed due to HttpClientErrorException. HTTP Response code: {} Message: {}", id, ex.getStatusCode(), ex.getResponseBodyAsString());
			throw new RuntimeException(ex);
		} catch (Exception ex) {
			LOGGER.error("Fetching album with id {} against spotify failed due to general Exception. Message: {}", id, ex.getMessage());
			throw ex;
		}
	}
	
	@Override
	public TracksResponseModel fetchTracks (String id, String market) {
		LOGGER.info("Fetching tracks for album with id {} against spotify for market {}", id, market);
		
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
			ResponseEntity<TracksResponseModel> response = restTemplate.exchange(getTracksEndpoint(), HttpMethod.GET, requestEntity, TracksResponseModel.class, id, market);
			
			LOGGER.info("Fetching tracks for album with id {} against spotify succesful with status {}", id, response.getStatusCode());
			return response.getBody();
			
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Fetching tracks for album with id {} against spotify failed due to HttpClientErrorException. HTTP Response code: {} Message: {}", id, ex.getStatusCode(), ex.getResponseBodyAsString());
			throw new RuntimeException(ex);
		} catch (Exception ex) {
			LOGGER.error("Fetching tracks for album with id {} against spotify failed due to general Exception. Message: {}", id, ex.getMessage());
			throw ex;
		}
	}
	
	private String getAlbumEndpoint () {
		StringBuilder builder = new StringBuilder();
		
		return builder.append(SpotifyAPIEndpoints.BASE_PATH)
						.append(SpotifyAPIEndpoints.ALBUMS_ENDPOINT)
						.append("/{id}")
						.append("?market={market}")
						.toString();
	}
	
	private String getTracksEndpoint () {
		StringBuilder builder = new StringBuilder();
		
		return builder.append(SpotifyAPIEndpoints.BASE_PATH)
						.append(SpotifyAPIEndpoints.ALBUMS_ENDPOINT)
						.append("/{id}")
						.append(SpotifyAPIEndpoints.TRACKS_ENDPOINT)
						.append("?market={market}")
						.toString();
	}
	
}
