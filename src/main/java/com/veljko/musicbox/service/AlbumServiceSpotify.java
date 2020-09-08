package com.veljko.musicbox.service;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

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
	public Album fetchAlbum (String id, Optional<String> marketOpt) {
		LOGGER.info("Fetching album with id {} against spotify for market {}", id, marketOpt.orElseGet(() -> "NOT_SET"));
		
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
			ResponseEntity<Album> response = restTemplate.exchange(getAlbumEndpoint(id, marketOpt), HttpMethod.GET, requestEntity, Album.class);
			
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
	public TracksResponseModel fetchTracks (String id, Optional<String> marketOpt) {
		LOGGER.info("Fetching tracks for album with id {} against spotify for market {}", id, marketOpt.orElseGet(() -> "NOT_SET"));
		
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
			ResponseEntity<TracksResponseModel> response = restTemplate.exchange(getTracksEndpoint(id, marketOpt), HttpMethod.GET, requestEntity, TracksResponseModel.class);
			
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
	
	private URI getAlbumEndpoint (String id, Optional<String> marketOpt) {
		String url = new StringBuilder()
				.append(SpotifyAPIEndpoints.BASE_PATH)
				.append(SpotifyAPIEndpoints.ALBUMS_ENDPOINT)
				.append("/{id}")
				.toString();
		
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", id);
		
		UriComponentsBuilder uriBulder = UriComponentsBuilder.fromUriString(url);
		
		if (marketOpt.isPresent()) {
			uriBulder.queryParam("market", marketOpt.get());
		}
		
		return uriBulder.buildAndExpand(pathParams).toUri();
	}
	
	private URI getTracksEndpoint (String id, Optional<String> marketOpt) {
		String url = new StringBuilder()
				.append(SpotifyAPIEndpoints.BASE_PATH)
				.append(SpotifyAPIEndpoints.ALBUMS_ENDPOINT)
				.append("/{id}")
				.append(SpotifyAPIEndpoints.TRACKS_ENDPOINT)
				.toString();
		
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", id);
		
		UriComponentsBuilder uriBulder = UriComponentsBuilder.fromUriString(url);
		
		if (marketOpt.isPresent()) {
			uriBulder.queryParam("market", marketOpt.get());
		}
		
		return uriBulder.buildAndExpand(pathParams).toUri();
	}
	
}
